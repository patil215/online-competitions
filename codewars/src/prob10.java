import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by patil215 on 3/7/15.
 */
public class prob10 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);


        int t = in.nextInt();

        for(int time = t;t>0;t--) {
            String out = "";
            int times = Integer.valueOf(in.next());
            for(int i=0;i<times;i++) {
                String temp = in.next();
                int a = Integer.parseInt(temp, 16);
                switch (a) {
                    case 0x81:
                        out += 'a';
                        break;
                    case 0x82:
                        out += 'b';
                        break;
                    case 0x83:
                        out += 'c';
                        break;
                    case 0x84:
                        out += 'd';
                        break;
                    case 0x85:
                        out += 'e';
                        break;
                    case 0x86:
                        out += 'f';
                        break;
                    case 0x87:
                        out += 'g';
                        break;
                    case 0x88:
                        out += 'h';
                        break;
                    case 0x89:
                        out += 'i';
                        break;
                    case 0x91:
                        out += 'j';
                        break;
                    case 0x92:
                        out += 'k';
                        break;
                    case 0x93:
                        out += 'l';
                        break;
                    case 0x94:
                        out += 'm';
                        break;
                    case 0x95:
                        out += 'n';
                        break;
                    case 0x96:
                        out += 'o';
                        break;
                    case 0x97:
                        out += 'p';
                        break;
                    case 0x98:
                        out += 'q';
                        break;
                    case 0x99:
                        out += 'r';
                        break;
                    case 0xA2:
                        out += 's';
                        break;
                    case 0xA3:
                        out += 't';
                        break;
                    case 0xA4:
                        out += 'u';
                        break;
                    case 0xA5:
                        out += 'v';
                        break;
                    case 0xA6:
                        out += 'w';
                        break;
                    case 0xA7:
                        out += 'x';
                        break;
                    case 0xA8:
                        out += 'y';
                        break;
                    case 0xA9:
                        out += 'z';
                        break;
                    case 0xC1:
                        out += 'A';
                        break;
                    case 0xC2:
                        out += 'B';
                        break;
                    case 0xC3:
                        out += 'C';
                        break;
                    case 0xC4:
                        out += 'D';
                        break;
                    case 0xC5:
                        out += 'E';
                        break;
                    case 0xC6:
                        out += 'F';
                        break;
                    case 0xC7:
                        out += 'G';
                        break;
                    case 0xC8:
                        out += 'H';
                        break;
                    case 0xC9:
                        out += 'I';
                        break;
                    case 0xD1:
                        out += 'J';
                        break;
                    case 0xD2:
                        out += 'K';
                        break;
                    case 0xD3:
                        out += 'L';
                        break;
                    case 0xD4:
                        out += 'M';
                        break;
                    case 0xD5:
                        out += 'N';
                        break;
                    case 0xD6:
                        out += 'O';
                        break;
                    case 0xD7:
                        out += 'P';
                        break;
                    case 0xD8:
                        out += 'Q';
                        break;
                    case 0xD9:
                        out += 'R';
                        break;
                    case 0xE2:
                        out += 'S';
                        break;
                    case 0xE3:
                        out += 'T';
                        break;
                    case 0xE4:
                        out += 'U';
                        break;
                    case 0xE5:
                        out += 'V';
                        break;
                    case 0xE6:
                        out += 'W';
                        break;
                    case 0xE7:
                        out += 'X';
                        break;
                    case 0xE8:
                        out += 'Y';
                        break;
                    case 0xE9:
                        out += 'Z';
                        break;
                    case 0x40:
                        out += ' ';
                        break;
                    case 0x4B:
                        out += '.';
                        break;
                    case 0x6B:
                        out += ',';
                        break;
                    case 0x5A:
                        out += '!';
                        break;

                }
            }
            System.out.println(out);
        }
    }
}
