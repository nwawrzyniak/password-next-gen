package nwawsoft.pwng.model;

public class CharacterSetFactory {
    private final CharacterSet cs;
    private char[] specialCharacters;

    public CharacterSetFactory(CharacterSet cs) {
        this.cs = cs;
    }

    public char[] getCharacterSet() {
        char[] set
                = null
                ;
        switch (cs) {
            case OLD:
                set = new char[33];
                set[0]=' ';
                set[1]='!';
                set[2]='"';
                set[3]='#';
                set[4]='$';
                set[5]='%';
                set[6]='&';
                set[7]='\'';
                set[8]='(';
                set[9]=')';
                set[10]='*';
                set[11]='+';
                set[12]=',';
                set[13]='-';
                set[14]='.';
                set[15]='/';
                set[16]=':';
                set[17]=';';
                set[18]='<';
                set[19]='=';
                set[20]='>';
                set[21]='?';
                set[22]='@';
                set[23]='[';
                set[24]='\\';
                set[25]=']';
                set[26]='^';
                set[27]='_';
                set[28]='`';
                set[29]='{';
                set[30]='|';
                set[31]='}';
                set[32]='~';
                break;
            case EASY_GERMAN:
                break;
            case EASY_ENGLISH:
                break;
            case FULL:
                set = new char[10000];
                set[0]=' ';
                set[1]='!';
                set[2]='"';
                set[3]='#';
                set[4]='$';
                set[5]='%';
                set[6]='&';
                set[7]='\'';
                set[8]='(';
                set[9]=')';
                set[10]='*';
                set[11]='+';
                set[12]=',';
                set[13]='-';
                set[14]='.';
                set[15]='/';
                set[16]=':';
                set[17]=';';
                set[18]='<';
                set[19]='=';
                set[20]='>';
                set[21]='?';
                set[22]='@';
                set[23]='[';
                set[24]='\\';
                set[25]=']';
                set[26]='^';
                set[27]='_';
                set[28]='`';
                set[29]='{';
                set[30]='|';
                set[31]='}';
                set[32]='~';
                break;
            case MINIMALISTIC:
                break;
            case OPTIMIZED:
                break;
        }
        return set;
    }
}
