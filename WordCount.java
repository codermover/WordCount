
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
        boolean hasOptionA = false;
        boolean hasOptionC = false;;
        boolean hasOptionW = false;;
        boolean hasOptionL = false;;
        boolean hasOptionS = false;;
        boolean hasOptionE = false;;
        boolean hasOptionO = false;;

        String fileParameter = "";
        String sParameter = "";
        String oParameter = "";
        String eParameter = "";
        for(int i = 0; i < args.length; i++)
        {
            String str = args[i];

            if (str.equals("-a")) hasOptionA = true;
            if (str.equals("-c")) hasOptionC = true;
            if (str.equals("-w")) hasOptionW = true;
            if (str.equals("-l")) hasOptionL = true;
            if (str.equals("-s")) hasOptionS = true;

            if (str.equals("-e"))
            {
                hasOptionE = true;
                eParameter = args[i+1];
                i++;
            }
            if (str.equals("-o"))
            {
                hasOptionO = true;
                oParameter = args[i+1];
                i++;
            }
            else
            {
                fileParameter = str;
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


        if(hasOptionS){

            wordCount.getFileList(fileParameter);

            for(File f : wordCount.fileList) {
                System.out.println(f.getAbsolutePath());

                wordCount.read(f.getAbsolutePath());
                wordCount.fn = f.getName();
                if (hasOptionC) {
                    System.out.println(wordCount.fn + "," + "字符数：" + wordCount.countChar());
                }

                if (hasOptionW && !hasOptionE) {
                    System.out.println(wordCount.fn + "," + "单词数：" + wordCount.countWord());
                }

                if (hasOptionL) {
                    System.out.println(wordCount.fn + "," + "行数：" + wordCount.countLine());
                }

                if (hasOptionE) {
                    wordCount.setStopListFile(eParameter);
                    System.out.println(wordCount.fn + "," + "单词数：" + wordCount.countWStopList());
                }
                if (hasOptionA) {
                    System.out.printf("%s,代码行/空行/注释行：%d/%d/%d\n", wordCount.fn, wordCount.countCodeLine(), wordCount.countEmptyLine(), wordCount.countCommentLine());
                }
            }

        }
        else
        {
            wordCount.fn = fileParameter;
            File root = new File("");
            wordCount.read(root.getAbsolutePath() + "\\" + wordCount.fn);
            if (hasOptionA) {
                System.out.printf("%s,代码行/空行/注释行：%d/%d/%d", wordCount.fn, wordCount.countCodeLine(), wordCount.countEmptyLine(), wordCount.countCommentLine());
            }
            if (hasOptionC) {
                System.out.println(wordCount.fn + "," + "字符数：" + wordCount.countChar());
            }

            if (hasOptionW && !hasOptionE) {
                System.out.println(wordCount.fn + "," + "单词数：" + wordCount.countWord());
            }

            if (hasOptionL) {
                System.out.println(wordCount.fn + "," + "行数：" + wordCount.countLine());
            }

            if (hasOptionE) {
                wordCount.setStopListFile(eParameter);
                System.out.println(wordCount.fn + "," + "单词数：" + wordCount.countWStopList());
            }

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

    public int countEmptyLine()
    {
        if(buffer.length() == 0)    return 0;
        String regex = "[\\s]*[\\S]?[\\s]*";
        Pattern emptyLinePattern = Pattern.compile(regex);

        String[] lines = buffer.split("\n", -1);

        Matcher matcher;
        int result = 0;
        for(String line : lines)
        {
            matcher = emptyLinePattern.matcher(line);
            if(matcher.matches())
                result++;
        }
        return result;

    }



    public int countWStopList()
    {
        if(buffer.length() == 0)    return 0;

        String[] stopWords = sl.split("[ ]");
        List<String> stopWordList = Arrays.asList(stopWords);
        String[] words = buffer.split("[, \t\n]+");

        int result = words.length;

        for(String word : words)
        {
            if(stopWordList.contains(word))
            {
                result--;
            }
        }

        return result;
    }
    public int countCommentLine()
    {
        if(buffer.length() == 0)    return 0;

        int result = 0;
        String regex = "([\\S]?/[/*][\\S]*)|\\*/";
        Pattern commentLinePattern = Pattern.compile(regex);
        Matcher matcher;
        String[] lines = buffer.split("\n", -1);
        for(String line : lines)
        {
            matcher = commentLinePattern.matcher(line);
            if(matcher.matches())
                result++;
        }
        return result;
    }
    public void getFileList(String path)
    {
        String searchPath = new String();
        String filePattern = new String();

        Pattern single = Pattern.compile("\\*\\.\\S+");
        Matcher singleMatcher = single.matcher(path);

        Pattern pathPattern = Pattern.compile("\\S+\\*\\.\\S+");
        Matcher pathMatcher = pathPattern.matcher(path);

        if(singleMatcher.matches())
        {
            searchPath = (new File("").getAbsolutePath());
            filePattern = path.substring(path.indexOf('*'));
        }
        if(pathMatcher.matches())
        {
            searchPath = path.substring(0, path.indexOf('*'));
            filePattern = path.substring(path.indexOf('*'));
        }

        File root = new File(searchPath);

        for(File fl : root.listFiles())
        {

            if(fl.isFile())
            {
                if(Pattern.compile(filePattern.replace("*.", "[a-zA-z0-9]+\\.")).matcher(fl.getName()).matches())
                    fileList.add(fl);

            }
            if(fl.isDirectory())
            {
                String temp = fl.getAbsolutePath() + "\\" + filePattern;
                System.out.println(temp);
                getFileList(temp);
            }


        }

    }








}
