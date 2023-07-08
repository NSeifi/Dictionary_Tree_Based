package dictree;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Tree tr=new Tree();
        System.out.println("CHOOSE ONE OF CHOICES \n 1=DEFAULT DICTIONERY \n 2=ADD TO DICTIONERY \n 3=BOTH DICTIONERY");
        Scanner sca=new Scanner(System.in);
                String select=sca.next();
                String address="";
                if(select.equals("1"))
                    address="E:\\wordlist.txt";
                if(select.equals("2"))
                {
                System.out.println("please enter your address:");
                address=sca.next();
                }
        Scanner ReadWordDic=new Scanner(new File(address));
        System.out.println("Please Wait Until Lode Dictionary Words...");
        ReadWordDic.next();
        while(ReadWordDic.hasNext())
        tr.Add_Node(ReadWordDic.next());
        System.out.println("Dictionary Words Loaded.");
        if(select.equals("3"))//both
        {
        Scanner ReadWordDic1=new Scanner(new File("E:\\wordlist.txt"));
        System.out.println("Please Wait Until Lode Dictionary Words...");
        ReadWordDic1.next();
        while(ReadWordDic1.hasNext())
        tr.Add_Node(ReadWordDic1.next());
         System.out.println("please enter your address of your dictionary:");
         address=sca.next();
        Scanner ReadWordDic2=new Scanner(new File(address));
        while(ReadWordDic2.hasNext())
        tr.Add_Node(ReadWordDic2.next());
        System.out.println("Dictionary Words Loaded.");
        }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        String LineWord="";String[] SplitLineWords;int CounterLine=1,LengthLine=0;
        Scanner ReadText=new Scanner(new File("E:\\big.txt"));
        tr.AddressOutputFile="E:\\Output.txt";
       while(ReadText.hasNextLine())
        {
            LineWord=ReadText.nextLine();
            SplitLineWords=LineWord.split(" ");
            LengthLine=SplitLineWords.length;
            for(int i=0;i<LengthLine;i++)
            {
                String Word=SplitLineWords[i];
                System.out.println(Word+"\r\n");
                if(!tr.SearchStr(Word))
                {
                    String ListWord=tr.lost(Word)+""+tr.extra(Word)+""+tr.mistake(Word)+""+tr.change(Word)+""+tr.separate(Word);
                    tr.SaveStringInFile(Word,ListWord , CounterLine);
                }
            }
         System.out.println(CounterLine+"\r\n");
            CounterLine++;
        }
       
     tr.show("E:\\Output.txt");
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }
}
