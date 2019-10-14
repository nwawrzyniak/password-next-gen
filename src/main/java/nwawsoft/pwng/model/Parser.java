package nwawsoft.pwng.model;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Parser 
{
  Random rand;
  char[] szArray = new char[33];
  
  public Parser()   
  {
    szArray[0]=' ';
    szArray[1]='!';
    szArray[2]='"';
    szArray[3]='#';
    szArray[4]='$';
    szArray[5]='%';
    szArray[6]='&';
    szArray[7]='\'';
    szArray[8]='(';
    szArray[9]=')';
    szArray[10]='*';
    szArray[11]='+';
    szArray[12]=',';
    szArray[13]='-';
    szArray[14]='.';
    szArray[15]='/';
    szArray[16]=':';
    szArray[17]=';';
    szArray[18]='<';
    szArray[19]='=';
    szArray[20]='>';
    szArray[21]='?';
    szArray[22]='@';
    szArray[23]='[';
    szArray[24]='\\';
    szArray[25]=']';
    szArray[26]='^';
    szArray[27]='_';
    szArray[28]='`';
    szArray[29]='{';
      szArray[30]='|';
    szArray[31]='}';
    szArray[32]='~';
  }
  
  public boolean isLower(char input)
  {
    if(input >= 'a' && input <= 'z')
    {
      return true;
    }
    return false;
  }
  
  public boolean isUpper(char input)
  {
    if(input >= 'A' && input <= 'Z')
    {
      return true;
    }
    return false;
  }
  
  public boolean isSZ(char input)
  {
    for(int i = 0; i < szArray.length; i++)
    {
      if(input == szArray[i])
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean isDigit(char input)
  {
    if(input >= '0' && input <= '9')
    {
      return true;
    }
    return false;
  }
  
  public boolean hasLower(String input)
  {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isLower(inputArray[i]))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasUpper(String input)
  {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isUpper(inputArray[i]))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasSZ(String input)
  {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isSZ(inputArray[i]))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasDigit(String input)
  {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isDigit(inputArray[i]))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean has2(String input)
  {
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSZ    = false;
    boolean hasDigit = false;
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isLower(inputArray[i]))
      {
        hasLower = true;
      } 
      if(isUpper(inputArray[i]))
      {
        hasUpper = true;
      } 
      if(isSZ(inputArray[i]))
      {
        hasSZ = true;
      } 
      if(isDigit(inputArray[i]))
      {
        hasDigit = true;
      }
    }
    if (hasLower && hasUpper)
    {
      return true;
    }
    if (hasLower && hasSZ)
    {
      return true;
    }
    if (hasLower && hasDigit)
    {
      return true;
    }
    if (hasUpper && hasSZ)
    {
      return true;
    }
    if (hasUpper && hasDigit)
    {
      return true;
    }
    if (hasSZ && hasDigit)
    {
      return true;
    }
    return false;
  }
  
  public boolean has3(String input)
  {
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSZ    = false;
    boolean hasDigit = false;
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isLower(inputArray[i]))
      {
        hasLower = true;
      } 
      if(isUpper(inputArray[i]))
      {
        hasUpper = true;
      } 
      if(isSZ(inputArray[i]))
      {
        hasSZ = true;
      } 
      if(isDigit(inputArray[i]))
      {
        hasDigit = true;
      }
    }
    if (hasLower && hasUpper && hasDigit)
    {
      return true;
    }
    if (hasLower && hasUpper && hasSZ)
    {
      return true;
    }
    if (hasLower && hasSZ && hasDigit)
    {
      return true;
    }
    if (hasUpper && hasSZ && hasDigit)
    {
      return true;
    }
    return false;
  }
  
  public boolean hasEverything(String input)
  {
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSZ    = false;
    boolean hasDigit = false;
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++)
    {
      if(isLower(inputArray[i]))
      {
        hasLower = true;
      } 
      if(isUpper(inputArray[i]))
      {
        hasUpper = true;
      } 
      if(isSZ(inputArray[i]))
      {
        hasSZ = true;
      } 
      if(isDigit(inputArray[i]))
      {
        hasDigit = true;
      } 
    }
    if (hasLower && hasUpper && hasSZ && hasDigit)
    {
      return true;
    }
    return false;
  }
  
  public boolean has8changes(String pLine)
  {
    int lZustand = 0;
    char symbol;
    int lZaehler = 0;
    int wechsel = 0;
    while (lZaehler < pLine.length())
    {
      symbol = pLine.charAt(lZaehler);
      if (lZustand == 0)
      {
        if(symbol >= 'a' && symbol <= 'z')
        {
          lZustand = 1;
        }
        if(symbol >= 'A' && symbol <= 'Z')
        {
          lZustand = 2;
        }
        if(isSZ(symbol))
        {
          lZustand = 3;
        }
        if(symbol >= '0' && symbol <= '9')
        {
          lZustand = 4;
        }
      }
      else if (lZustand == 1)
      {
        if(symbol >= 'A' && symbol <= 'Z')
        {
          lZustand = 2;
          wechsel++;
        }
        if(isSZ(symbol))
        {
          lZustand = 3;
          wechsel++;
        }
        if(symbol >= '0' && symbol <= '9')
        {
          lZustand = 4;
          wechsel++;
        }
      }
      else if (lZustand == 2)
      {
        if(symbol >= 'a' && symbol <= 'z')
        {
          lZustand = 1;
          wechsel++;
        }
        if(isSZ(symbol))
        {
          lZustand = 3;
          wechsel++;
        }
        if(symbol >= '0' && symbol <= '9')
        {
          lZustand = 4;
          wechsel++;
        }
      }
      else if (lZustand == 3)
      {
        if(symbol >= 'a' && symbol <= 'z')
        {
          lZustand = 1;
          wechsel++;
        }
        if(symbol >= 'A' && symbol <= 'Z')
        {
          lZustand = 2;
          wechsel++;
        }
        if(symbol >= '0' && symbol <= '9')
        {
          lZustand = 4;
          wechsel++;
        }
      }
      else if (lZustand == 4)
      {
        if(symbol >= 'a' && symbol <= 'z')
        {
          lZustand = 1;
          wechsel++;
        }
        if(symbol >= 'A' && symbol <= 'Z')
        {
          lZustand = 2;
          wechsel++;
        }
        if(isSZ(symbol))
        {
          lZustand = 3;
          wechsel++;
        }
      }
      lZaehler = lZaehler+1;
    }
    if (wechsel >= 8)
    {
      return lZustand == 1 || lZustand == 2 || lZustand == 3 || lZustand == 4;
    }
    return false;
  }
  
  public boolean parse1(String pLine)
  {
    if(pLine.length() >= 5)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  public boolean parse2(String pLine)
  {
    if (pLine.length()>=8)
    {
      if (has2(pLine))
      {
        return true;
      }
    }
    return false;
  }
  
  
  public boolean parse3(String pLine)
  {
    if (pLine.length()>=10)
    {
      if (has3(pLine))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean parse4(String pLine)
  {
    if (pLine.length()>=12)
    {
      if (hasEverything(pLine))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean parse5(String pLine)
  {
    if (pLine.length()>=14)
    {
      int lZustand = 0;
      char symbol;
      int lZaehler = 0;
      int wechsel = 0;
      while (lZaehler < pLine.length())
      {
        symbol = pLine.charAt(lZaehler);
        if (lZustand == 0)
        {
          if(symbol >= 'a' && symbol <= 'z')
          {
            lZustand = 1;
          }
          if(symbol >= 'A' && symbol <= 'Z')
          {
            lZustand = 2;
          }
          if(isSZ(symbol))
          {
            lZustand = 3;
          }
          if(symbol >= '0' && symbol <= '9')
          {
            lZustand = 4;
          }
        }
        else if (lZustand == 1)
        {
          if(symbol >= 'A' && symbol <= 'Z')
          {
            lZustand = 2;
            wechsel++;
          }
          if(isSZ(symbol))
          {
            lZustand = 3;
            wechsel++;
          }
          if(symbol >= '0' && symbol <= '9')
          {
            lZustand = 4;
            wechsel++;
          }
        }
        else if (lZustand == 2)
        {
          if(symbol >= 'a' && symbol <= 'z')
          {
            lZustand = 1;
            wechsel++;
          }
          if(isSZ(symbol))
          {
            lZustand = 3;
            wechsel++;
          }
          if(symbol >= '0' && symbol <= '9')
          {
            lZustand = 4;
            wechsel++;
          }
        }
        else if (lZustand == 3)
        {
          if(symbol >= 'a' && symbol <= 'z')
          {
            lZustand = 1;
            wechsel++;
          }
          if(symbol >= 'A' && symbol <= 'Z')
          {
            lZustand = 2;
            wechsel++;
          }
          if(symbol >= '0' && symbol <= '9')
          {
            lZustand = 4;
            wechsel++;
          }
        }
        else if (lZustand == 4)
        {
          if(symbol >= 'a' && symbol <= 'z')
          {
            lZustand = 1;
            wechsel++;
          }
          if(symbol >= 'A' && symbol <= 'Z')
          {
            lZustand = 2;
            wechsel++;
          }
          if(isSZ(symbol))
          {
            lZustand = 3;
            wechsel++;
          }
        }
        lZaehler = lZaehler+1;
      }
      if (wechsel >= 8)
      {
        return lZustand == 1 || lZustand == 2 || lZustand == 3 || lZustand == 4;
      }
      return false;
    }
    return false;
  }
  
  public String createLevel5()
  {
    rand = new Random(); // Randomizer
    String output = ""; //String, der nach und nach mit dem Passwort gefüllt wird
    int wantedLength = rand.nextInt(5)+14; //Erzeugt eine Passwortlänge zwischen 14 und 18 Zeichen
    char[] symbol = new char[1];
    int lastUse = -1;
    
    while(output.length() < wantedLength)
    {
      int use = rand.nextInt(4);
      symbol[0] = 'Ö';
      if((use == 0) && (lastUse != 0))
      {
        int a = rand.nextInt(26)+97;
        symbol = Character.toChars(a);
      }
      if((use == 1) && (lastUse != 1))
      {
        int a = rand.nextInt(26)+65;
        symbol = Character.toChars(a);
      }
      if((use == 2) && (lastUse != 2))
      {
        int a = rand.nextInt(10)+48;
        symbol = Character.toChars(a);
      }
      if((use == 3) && (lastUse != 3))
      {
        int a = rand.nextInt(5)+91;
        symbol = Character.toChars(a);
      }
      if(use != lastUse)
      {
        output = output + symbol[0];
      }
      lastUse = use;
    }
    if(hasEverything(output))
    {
      return output;
    }
    else
    {
      return createLevel5();
    }
  }
  
  public boolean dictionaryCheck(String pLine)
  {
    BufferedReader myReader;
    String currentLine;
    try
    {
      myReader = new BufferedReader(new FileReader("dictionary_min_5.txt"));
      while((currentLine = myReader.readLine()) != null)
      {
        if(pLine.toLowerCase().contains(currentLine.toLowerCase()))
        {
          return false;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("Fehler: " + e);
    }
    return true;
  }
}
