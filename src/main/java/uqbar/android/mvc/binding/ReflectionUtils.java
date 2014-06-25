package uqbar.android.mvc.binding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class ReflectionUtils {

	public static Method getMethod(Class<?> aClass, String methodName, Class<?>[] parameterTypes) {
		try {
			return aClass.getMethod(methodName, parameterTypes);
		}
		catch (SecurityException e) {
			throw ProgramException.wrap(e);
		}
		catch (NoSuchMethodException e) {
			throw ProgramException.wrap(e);
		}
	}

	public static <T> T invoke(final Object object, final Method method, final Object... params) {
		try {
			return (T) method.invoke(object, params);
		}
		catch (final InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		}
		catch (final Exception e) {
			throw new RuntimeException("Cannot invoke method", e);
		}
	}

	public static <T> T invoke(final Class<?> model, final String actionName) {
		try {
			return (T) findMethod(model, actionName, new Class[] {}).invoke(model, new Object[] {});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T invoke(final Object model, final String actionName, final Class<?>[] c, final Object... args) {
		return invoke(model, findMethod(model.getClass(), actionName, c), args);
	}

	public static <T> T invoke(Object target, String method, Object... args) {
		Class<?>[] clazz = new Class[args.length];
		int i = 0;
		for (Object object : args) {
			clazz[i] = object.getClass();
			i++;
		}
		return invoke(target, method, clazz, args);
	}

	public static Method findMethod(Class<?> clazz, String name, Class<?>[] paramTypes) {
		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			if (name.equals(method.getName()) && paramTypes.length == method.getParameterTypes().length) {
				boolean found = true;
				Class<?>[] methodParameterTypes = method.getParameterTypes();
				for (int j = 0; j < methodParameterTypes.length; j++) {
					found = methodParameterTypes[j].isAssignableFrom(paramTypes[j]);
					if (!found) {
						break;
					}
				}

				if (found) {
					method.setAccessible(true);
					return method;
				}
			}
		}
		return null;
	}

	public static void setProperty(Object anObject, String propertyName, Object aValue) {
		try {
			propertyMethod(anObject, propertyName, "set", aValue, true);
		}
		catch (SecurityException e) {
			throw ProgramException.wrap(e);
		}
		catch (IllegalArgumentException e) {
			throw ProgramException.wrap(e);
		}
	}

	public static Object getProperty(Object anObject, String propertyName) {
		try {
			return propertyMethod(anObject, propertyName, "get", null, false);
		}
		catch (SecurityException e) {
			throw ProgramException.wrap(e);
		}
		catch (IllegalArgumentException e) {
			throw ProgramException.wrap(e);
		}
	}

	public static String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	protected static Object propertyMethod(Object anObject, String propertyName, String type, Object param,
			boolean haveParam) {
		try {
			Class<?> aClass = anObject.getClass();
			Method accessor = null;
			Object nestedObject = anObject;

			if (propertyName.contains(".")) {
				String[] nestedProperties = propertyName.split("\\.");

				for (int i = 0; i < nestedProperties.length - 1; i++) {
					accessor = getMethod(aClass, "get" + capitalize(nestedProperties[i]), new Class[] {});
					accessor.setAccessible(true);
					nestedObject = invoke(nestedObject, accessor);
					if (nestedObject == null) {
						break;
					}
					aClass = nestedObject.getClass();
				}
				if (nestedObject != null) {
					String name = nestedProperties[nestedProperties.length - 1];
					nestedObject = invoke(nestedObject, type + capitalize(name), param, haveParam);
				}

			}
			else {
				nestedObject = invoke(nestedObject, type + capitalize(propertyName), param, haveParam);
			}

			return nestedObject;
		}
		catch (SecurityException e) {
			throw ProgramException.wrap(e);
		}
		catch (IllegalArgumentException e) {
			throw ProgramException.wrap(e);
		}
	}

	protected static Object invoke(Object nestedObject, String method, Object param, boolean haveParams) {
		Class<?>[] paramsType;
		if (!haveParams) {
			paramsType = new Class[] {};
		}
		else {
			paramsType = new Class[] { param.getClass() };
		}

		Method accessor = getMethod(nestedObject.getClass(), method, paramsType);
		accessor.setAccessible(true);

		if (!haveParams) {
			return invoke(nestedObject, accessor);
		}
		else {
			return invoke(nestedObject, accessor, param);
		}
	}
}
