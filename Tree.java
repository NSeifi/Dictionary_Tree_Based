package dictree;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class Tree {
public static int NumberNode;
public static  Object[] LinkedList;
public static String Suggestion;
public static String AddressOutputFile;

//Nod[0][.] := 0==bedone dadeh, 1==vasate dadeh, 2==entehaie yek dadeh
//Nod[1][.] := Esharegar Be Node Badi Dar Linked List(Shomarandeh)
public Tree(){
    NumberNode=0;
    LinkedList=new Object[0];
    Suggestion="";
    AddressOutputFile="";
}
public void Add_Node(String Word){
   String SlackWord=Word.toUpperCase();
   char[] SplitWord=SlackWord.toCharArray();
   int LengthWord=SplitWord.length , NextLeves=0,NowPosition=0,NowLevels=0;
   int[] intWord=new int[LengthWord];
   for(int j=0;j<LengthWord;j++)
       intWord[j]=(int)SplitWord[j]-65;
   //Search Roie Tree Age Nabod Add Shavad
   for(int i=0;i<LengthWord;i++)
     {
        if(NumberNode>0)
           {
              NowLevels=NextLeves;
              NowPosition=((Node)LinkedList[NowLevels]).Nd[0][intWord[i]];
              NextLeves=((Node)LinkedList[NowLevels]).Nd[1][intWord[i]];
              if(NowPosition>0)
                 {
                     if(i==LengthWord-1)
                        ((Node)LinkedList[NowLevels]).Nd[0][intWord[i]]=2;
                     else if(NextLeves==0)
                     {
                         ((Node)LinkedList[NowLevels]).Nd[1][intWord[i]]=NumberNode;
                         Node nd1=new Node();
                         nd1.Nd[0][intWord[i+1]]=1;
                         NextLeves=NumberNode;
                         LLAdd(nd1);
                     }
                 }
              else
                 {
                  if(i==LengthWord-1)
                        ((Node)LinkedList[NowLevels]).Nd[0][intWord[i]]=2;
                  else
                  {
                        ((Node)LinkedList[NowLevels]).Nd[0][intWord[i]]=1;
                        ((Node)LinkedList[NowLevels]).Nd[1][intWord[i]]=NumberNode;
                         Node nd2=new Node();
                         nd2.Nd[0][intWord[i+1]]=1;
                         NextLeves=NumberNode;
                         LLAdd(nd2);
                  }
                 }
           }
       else
           {
              Node nd0=new Node();
              LLAdd(nd0);
              i=-1;
           }
}
}
private void LLAdd(Node nd){
    NumberNode++;
    Object[] Slack=new Object[NumberNode];
    for(int i=0;i<NumberNode-1;i++)
    Slack[i]=LinkedList[i];
    Slack[NumberNode-1]=nd;
    LinkedList=Slack;
}
public boolean  SearchStr(String str){
    String SlackWord=str.toUpperCase();
   char[] SplitWord=SlackWord.toCharArray();
   int LengthWord=SplitWord.length , NextLeves=0,NowPosition=0;
   int[] intWord=new int[LengthWord];
   for(int j=0;j<LengthWord;j++)
       intWord[j]=(int)SplitWord[j]-65;
   int SrchNod=0;
   boolean Exist=false;
   for(int i=0;i<LengthWord;i++)
   {
       if(intWord[i]>=0 && intWord[i]<26)
       {
           NowPosition=((Node)LinkedList[SrchNod]).Nd[0][intWord[i]];
           NextLeves=((Node)LinkedList[SrchNod]).Nd[1][intWord[i]];
           if(NowPosition>0)
           {
               if(i==LengthWord-1)
               {
                   if(NowPosition==2)
                       Exist=true;
               }
               else if(NextLeves>0)
                   SrchNod= ((Node)LinkedList[SrchNod]).Nd[1][intWord[i]];
           }
           else
               break;
       }
   }
   return Exist;
}
public String lost(String MistakeSpel){
      Suggestion="";
      int count=0;
    char[] SplitMistakeSpel=MistakeSpel.toCharArray();
    String CorrectSpel="";
    int LenghtMistakeSpel=SplitMistakeSpel.length;
    for(int i=0;i<=LenghtMistakeSpel;i++)
        for(int j=65;j<91;j++)
        {
            CorrectSpel="";
            for(int k=0;k<LenghtMistakeSpel;k++)
            {
                char ch=(char)j;
                if(i==k)
                    CorrectSpel=CorrectSpel+ch+SplitMistakeSpel[k];
                else
                    CorrectSpel=CorrectSpel+SplitMistakeSpel[k];
            }
            if(SearchStr(CorrectSpel))
                Suggestion+=CorrectSpel+"\t";
            if(count<26)
            {
            CorrectSpel="";
            for(int kk=0;kk<LenghtMistakeSpel;kk++)
            {
             CorrectSpel=CorrectSpel+SplitMistakeSpel[kk];
            }
             CorrectSpel+=(char)j;
            if(SearchStr(CorrectSpel))
                Suggestion+=CorrectSpel+"\t";
            count++;
            }
        }
    return Suggestion;
}
public String extra(String MistakeSpel){
    Suggestion="";
    char[] SplitMistakeSpel=MistakeSpel.toCharArray();
    String CorrectSpel="";
    int LenghtMistakeSpel=SplitMistakeSpel.length;
    for(int i=0;i<LenghtMistakeSpel;i++)
        {
            CorrectSpel="";
            for(int k=0;k<LenghtMistakeSpel;k++)
                if(i!=k)
                    CorrectSpel=CorrectSpel+SplitMistakeSpel[k];
            if(SearchStr(CorrectSpel))
                Suggestion+=CorrectSpel+"\t";
        }
    return Suggestion;
}
public String mistake(String MistakeSpel){
    Suggestion="";
    char[] SplitMistakeSpel=MistakeSpel.toCharArray();
    String CorrectSpel="";
    int LenghtMistakeSpel=SplitMistakeSpel.length;
    for(int i=0;i<LenghtMistakeSpel;i++)
        for(int j=65;j<91;j++)
        {
            CorrectSpel="";
            for(int k=0;k<LenghtMistakeSpel;k++)
            {char ch=(char)j;
                if(i==k)
                    CorrectSpel=CorrectSpel+ch;
                else
                    CorrectSpel=CorrectSpel+SplitMistakeSpel[k];
            }
            if(SearchStr(CorrectSpel))
                Suggestion+=CorrectSpel+"\t";
        }
    return Suggestion;
}
public String change(String MistakeSpel){
  Suggestion="";
    char[] SplitMistakeSpel=MistakeSpel.toCharArray();
    String CorrectSpel="";
    int LenghtMistakeSpel=SplitMistakeSpel.length;
    for(int i=0;i<LenghtMistakeSpel-1;i++)
    {
        CorrectSpel="";
        for(int k=0;k<LenghtMistakeSpel;k++)
        {
        if(i==k)
        {
            k++;
            CorrectSpel=CorrectSpel+SplitMistakeSpel[k]+SplitMistakeSpel[i];
        }
        else
            CorrectSpel=CorrectSpel+SplitMistakeSpel[k];
        }
        if(SearchStr(CorrectSpel))
            Suggestion+=CorrectSpel+"\t";
    }
    return Suggestion;
}
public String separate(String MistakeSpel){
   Suggestion="";
    char[] SplitMistakeSpel=MistakeSpel.toCharArray();
    String CorrectSpel_1="",CorrectSpel_2="";
    int LenghtMistakeSpel=SplitMistakeSpel.length;
    for(int i=1;i<LenghtMistakeSpel;i++)
    {
        CorrectSpel_1="";CorrectSpel_2="";
        for(int k=0;k<LenghtMistakeSpel;k++)
        {
        if(i>k)
            CorrectSpel_1=CorrectSpel_1+SplitMistakeSpel[k];
        else
            CorrectSpel_2=CorrectSpel_2+SplitMistakeSpel[k];
        }
        if(SearchStr(CorrectSpel_1)&&SearchStr(CorrectSpel_2))
            Suggestion+=CorrectSpel_1+" "+CorrectSpel_2+"\t";
    }
    return Suggestion;
}
public void SaveStringInFile(String MistakeSpel,String SuggestionWords,int Line)throws FileNotFoundException, IOException
{
    String Title="***********\r\nLine: "+Line+" missplled word: "+MistakeSpel+"\r\nSuggestions :\r\n";
    String[] WordsCorrect=SuggestionWords.split("\t");
    int lengthWords=WordsCorrect.length;
    FileWriter fw=new FileWriter(AddressOutputFile,true);
    fw.write(Title);
    for(int i=0;i<lengthWords;i++)
        fw.write(WordsCorrect[i]+"\r\n");
    fw.close();
}
public void show(String path) throws FileNotFoundException
{
    String txt="";
    Scanner scan=new Scanner(new File(path));
    while(scan.hasNextLine())
        txt+=scan.nextLine()+"\n";
    scan.close();
    JOptionPane.showMessageDialog(null,txt);
}

}