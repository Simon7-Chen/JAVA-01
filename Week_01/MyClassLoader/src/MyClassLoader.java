import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenzhibin
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class myClass = null;
        byte[] classDate = loadClassDate();
        if (classDate != null) {
            myClass = defineClass(name, classDate, 0, classDate.length);
        }
        return myClass;
    }

    private byte[] loadClassDate() {
        File file = new File(classPath);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file);
                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    for (int i = 0; i < size; i++) {
                        // 方式1 逻辑：将每个字节与0xFF求异或后并转成16进制字节，再将结果转为java的byte
                        //String strHex = Integer.toHexString(buffer[i] ^ 0xFF);
                        //buffer[i] = (byte)Integer.parseInt(strHex, strHex.length() -2, strHex.length(), 16);
                        // 方式2 逻辑：(255-(x+128))-128 = -x-1 方式1代码结果等同方式2代码结果
                        buffer[i] = (byte) (-buffer[i] - 1);
                    }
                    out.write(buffer, 0, size);
                }
                return out.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        //Hello.xlass文件路径
        String classPath = "C://Hello.xlass";

        //自定义类加载器
        MyClassLoader myClassLoader = new MyClassLoader(classPath);

        //类的全称
        String className = "Hello";

        //加载Hello这个class文件
        Class<?> helloClass = myClassLoader.loadClass(className);

        System.out.println("类加载器是:" + helloClass.getClassLoader());

        //利用反射获取hello方法
        Method method = helloClass.getDeclaredMethod("hello");
        Object object = helloClass.getDeclaredConstructor().newInstance();
        method.invoke(object);
    }
}
