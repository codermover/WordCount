# WordCount

----------------
环境
JRE 1.8及以上版本

使用方法

wc.exe -c file.c (-o result.txt) (-e escapeWordList.txt)

wc.exe -w file.c (-o result.txt) (-e escapeWordList.txt)

wc.exe -l file.c (-o result.txt) (-e escapeWordList.txt)

wc.exe -a file.c (-o result.txt) (-e escapeWordList.txt)

wc.exe -s file.c (-o result.txt) (-e escapeWordList.txt)

-c 统计字符数 
-w 统计单词数 
-l 统计行数 
-a 统计代码行数/空行数/注释行数 
-s 递归统计文件夹下所有符合条件的文件 
-o 指定输出文件名 
-e 统计字符时忽略该文件列表中的词 以空格分割
