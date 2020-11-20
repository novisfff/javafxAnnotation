package cn.novisfff.javafx;

import cn.novisfff.javafxExample.Main;
import org.omg.CORBA.ARG_OUT;

import java.io.DataOutput;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author ：zyf
 * @date ：Created in 2020/11/19
 * @description：
 * @modified By：
 * @version: $
 */

public class FxScanner {

    /**
     * all Files
     */
    private List<File> allFiles;

    /**
     * .class File
     */
    private List<File> classFiles = new ArrayList<>();

    /**
     * .xml File
     */
    private List<File> xmlFiles = new ArrayList<>();

    /**
     * class List
     */
    private List<String> classPathList = new ArrayList<>();

    /**
     * the primary Source
     * FxScanner can scan all Files under primarySources
     */
    private Class<?> primarySources;

    /**
     * The type of File which need to be handled
     */
    enum FileType {
        /**
         * .class File
         */
        CLASS(".+\\.class"),

        /**
         * .xml File
         */
        XML(".+\\.xml");

        private String regex;

        FileType(String regex) {
            this.regex = regex;
        }

        public String getRegex() {
            return regex;
        }
    }

    /**
     * Constructor
     * @param primarySources
     */
    public FxScanner(Class<?> primarySources) {
        this.primarySources = primarySources;
    }

    /**
     * Scan all File under primarySources, and add Files to fileList
     * @throws URISyntaxException
     */
    public void scan() throws URISyntaxException {
        URL resource = primarySources.getResource("");
        allFiles = scanFile(resource);
        sortFile();
        getClassPath();
    }

    /**
     * scan File
     * @param resource the url of primarySources
     * @return List<File>
     * @throws URISyntaxException
     */
    private List<File> scanFile(URL resource) throws URISyntaxException {
        List<File> fileList = new ArrayList<>();
        File root = new File(resource.toURI());
        scanFileDfs(fileList, root);
        return fileList;
    }

    /**
     * scan Files by DFS
     * @param fileList
     * @param file rootFile
     */
    private void scanFileDfs(List<File> fileList, File file) {
        if(file.isFile()) {
            fileList.add(file);
            return;
        }
        for (File subFile : file.listFiles()) {
            scanFileDfs(fileList, subFile);
        }
    }

    /**
     * sort allFile list, search .class file and .xml file
     */
    private void sortFile() {
        Pattern classFilePattern = Pattern.compile(FileType.CLASS.getRegex());
        Pattern xmlFilePattern = Pattern.compile(FileType.XML.getRegex());
        for (File file : allFiles) {
            if(classFilePattern.matcher(file.getName()).matches()) {
                classFiles.add(file);
            } else if(xmlFilePattern.matcher(file.getName()).matches()) {
                xmlFiles.add(file);
            }
        }
    }

    /**
     * get all class File's class path
     */
    private void getClassPath() {
        String rootPath = primarySources.getPackage().getName();
        for (File classFile : classFiles) {
            classPathList.add(rootPath + "." + classFile.getName().replace(".class", ""));
        }
    }

    public List<File> getAllFiles() {
        return allFiles;
    }

    public List<File> getClassFiles() {
        return classFiles;
    }

    public List<File> getXmlFiles() {
        return xmlFiles;
    }

    public List<String> getClassPathList() {
        return classPathList;
    }

    public static void main(String[] args) throws URISyntaxException {
        FxScanner fxScanner = new FxScanner(Main.class);
        fxScanner.scan();
        List<String> classPathList = fxScanner.getClassPathList();
        for (String s : classPathList) {
            System.out.println(s);
        }
    }

}
