package org.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * isInitialized:是否执行类的静态代码块
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }
    /**
     * 获取执行包名下所有的类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            // 获取packageName的url
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        // 获取路径，并且删除路径中多余的空格
                        String packagePath = url.getPath().replace("%20", "");
                        // String packagePath = URLDecoder.decode(url.getPath(),
                        // "utf-8");
                        addClass(classSet, packagePath, packageName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classSet;
    }

    /**
     * 
     */
    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        // 获取packagePath下所有的类文件或者文件夹
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
            }
        });
        for (File file : files) {
            String fileName=file.getName();
            // 如果是类文件，则加载并保存该类
            if(file.isFile()){
                String className=fileName.substring(0,fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {// 如果是文件夹，则递归加载并保存该文件夹下的类
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    public static void main(String[] args) {
        getClassSet("org.smart4j.framework");
    }
}
