
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WordCount{
    static int MAX_LINES = 4096;
    String fp;
    String fn;
    String sl;
    String buffer;
    List<File> fileList;
    public static void main(String args[]) throws Exception
    {
       Scanner input = new Scanner(System.in);
  System.out.println("please input path:");
  String path = input.next();
  int countChar = 0;
  int countword = 0;
  int countline = 0;
  InputStreamReader isr = new InputStreamReader(new FileInputStream(path));

  BufferedReader br = new BufferedReader(isr);
  while(br.read()!=-1)
  {
   String s = br.readLine();
   countChar += s.length();
   countword += s.split(" ").length;
   countline++;
  isr.close();//关闭文件
  System.out.println("char cont "+countChar);
  System.out.println("word count "+countword );
  System.out.println("line count "+countline); 
  }
}


        WordCount wordCount = new WordCount();

        if(hasOptionO)
        {
            String outputFile = oParameter;
            if(outputFile.isEmpty())
            {
                System.out.print("No output file.");
            }
            else
            {

                PrintStream ps=new PrintStream(wordCount.setfile(outputFile));
                System.setOut(ps);
            }
        }

  public static boolean ContainsStr(String s1, String s2)
{
    if(s1.indexOf(s2)>=0)
    {
        return true;
    } else{
        return false;
    }
}
    public static void main(String[] args) throws IOException
    {
   // write your code here
         File f=new File("e:/code.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String strRead=null;
        List<String> lstLines=new ArrayList<String>();
        int nLineNum =1;
        while((strRead=br.readLine())!=null)
        {
            lstLines.add(strRead);
        }
        System.out.println("请输入要查询的单词");
        Scanner sc=new Scanner(System.in);
        String strWord=sc.nextLine();
        for(String strTemp:lstLines)
        {
            boolean b=ContainsStr(strTemp,strWord);
            if(b)
            {
                System.out.println(nLineNum+":"+strTemp);
            }
            nLineNum++;
        }
    }
}
        else
        {String file = CodeCounter.class.getResource("/").getFile();  
        String path = file.replace("target/test-classes", "src");  
  
        ArrayList<File> al = getFile(new File(path));  
        for (File f : al) {  
            if (f.getName().matches(".*\\.java$")){ // 匹配java格式的文件  
                count(f);  
                System.out.println(f);  
            }  
        }  
        System.out.println("统计文件：" + files);  
        System.out.println("代码行数：" + codeLines);  
        System.out.println("注释行数：" + commentLines);  
        System.out.println("空白行数：" + blankLines);  
    }  
      
    static long files = 0;  
    static long codeLines = 0;  
    static long commentLines = 0;  
    static long blankLines = 0;  
    static ArrayList<File> fileArray = new ArrayList<File>();  
      
    /** 
     * 获得目录下的文件和子目录下的文件 
     * @param f 
     * @return 
     */  
    public static ArrayList<File> getFile(File f) {  
        File[] ff = f.listFiles();  
        for (File child : ff) {  
            if (child.isDirectory()) {  
                getFile(child);  
            } else  
                fileArray.add(child);  
        }  
        return fileArray;  
  
    }  
  
    /** 
     * 统计方法 
     * @param f 
     */  
    private static void count(File f) {  
        BufferedReader br = null;  
        boolean flag = false;  
        try {  
            br = new BufferedReader(new FileReader(f));  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                line = line.trim(); // 除去注释前的空格  
                if (line.matches("^[ ]*$")) { // 匹配空行  
                    blankLines++;  
                } else if (line.startsWith("//")) {  
                    commentLines++;  
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {  
                    commentLines++;  
                    flag = true;  
                } else if (line.startsWith("/*") && line.endsWith("*/")) {  
                    commentLines++;  
                } else if (flag == true) {  
                    commentLines++;  
                    if (line.endsWith("*/")) {  
                        flag = false;  
                    }  
                } else {  
                    codeLines++;  
                }  
            
            files++;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                    br = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  

    }
    WordCount()
    {
        buffer = new String();
        fileList = new ArrayList<>();
    }
    WordCount(String fileName)
    {

        this.fn = fileName;
        buffer = new String();
        fileList = new ArrayList<>();
    }

    public OutputStream setfile(String filename) {
                File root = new File("");
                //System.out.println(root.getAbsolutePath());
                File file = new File(root.getAbsolutePath() + '/' + filename);
                OutputStream outputStream;
                try {
                    return new FileOutputStream(file);
                } catch (IOException e) {
                    System.out.println(e);
        }
        return null;
    }
    public void read(String absolutePath) {

        File file = new File(absolutePath);
        buffer = "";
        try {
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();

            for(int i = 0; i < size; i++)
            {
                //buffer[i] = (char)inputStream.read();

                buffer += (char)inputStream.read();
            }

        } catch (IOException e) {
            System.out.println(e);
        }


    }
    public void setStopListFile(String filename)
    {
        File root = new File("");
        File file = new File(root.getAbsolutePath() + "\\" + filename);

        try {
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();

            for(int i = 0; i < size; i++)
            {


                sl += (char)inputStream.read();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public int countChar()
    {
        return buffer.length();
    }

    public int countLine()
    {
        if(buffer.length() == 0)
            return 0;

        String[] lines = buffer.split("\n", -1);
        return lines.length;
    }
    public int countWord()
    {
        if(buffer.length() == 0)
            return 0;

        String[] words = buffer.split("[, \t\n]+");
        return words.length;
    }

    public int countCodeLine()
    {
        if(buffer.length() == 0)    return 0;

        String[] lines = buffer.split("\n", -1);

        return lines.length - countCommentLine() - countEmptyLine();
    }

String file = CodeCounter.class.getResource("/").getFile();  
        String path = file.replace("target/test-classes", "src");  
  
        ArrayList<File> al = getFile(new File(path));  
        for (File f : al) {  
            if (f.getName().matches(".*\\.java$")){ // 匹配java格式的文件  
                count(f);  
                System.out.println(f);  
            }  
        }  
        System.out.println("统计文件：" + files);  
        System.out.println("代码行数：" + codeLines);  
        System.out.println("注释行数：" + commentLines);  
        System.out.println("空白行数：" + blankLines);  
    }  
      
    static long files = 0;  
    static long codeLines = 0;  
    static long commentLines = 0;  
    static long blankLines = 0;  
    static ArrayList<File> fileArray = new ArrayList<File>();  
   
       
    public static ArrayList<File> getFile(File f) {  
        File[] ff = f.listFiles();  
        for (File child : ff) {  
            if (child.isDirectory()) {  
                getFile(child);  
            } else  
                fileArray.add(child);  
        }  
        return fileArray;  
  
    }  
  

    private static void count(File f) {  
        BufferedReader br = null;  
        boolean flag = false;  
        try {  
            br = new BufferedReader(new FileReader(f));  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                line = line.trim(); 
                if (line.matches("^[ ]*$")) { 
                    blankLines++;  
                } else if (line.startsWith("//")) {  
                    commentLines++;  
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {  
                    commentLines++;  
                    flag = true;  
                } else if (line.startsWith("/*") && line.endsWith("*/")) {  
                    commentLines++;  
                } else if (flag == true) {  
                    commentLines++;  
                    if (line.endsWith("*/")) {  
                        flag = false;  
                    }  
                } else {  
                    codeLines++;  
                }  
            }  
            files++;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                    br = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  

                    }
