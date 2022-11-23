import java.util.Scanner;

public class CompressionTest {
    Scanner scanner = new Scanner(System.in);
    CharTable charTable = new CharTable();
    HuffmanTree huffTree;


    public void start() {
        boolean runProgram = true;
        while (runProgram) {
            System.out.println("1 – Comprimir texto \n0 – Sair\n------------------------");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Escreva a palavra: ");
                    String text = scanner.next();

                    compresstext(text);
                    String binaryText = convertToBinary(text);
                    System.out.println("BINARIO(ASCII) - " + binaryText + " com " + countBytes(binaryText) );
                    String compressedText = convertToCompress(text);
                    System.out.println("HUFFMAN - " + compressedText + " com " + countBytes(compressedText) );
                    double compressRate = (double)countBytes(compressedText) / countBytes(binaryText);
                    System.out.println("Taxa de compressão: " + compressRate);
                    System.out.println("------------------------");
                    break;
                case 0:
                    runProgram = false;
                    break;
                default:
                    break;
            }
        }
        
        
    }

    public void compresstext(String string) {

        System.out.println("A palavra é: " + string + " e tem " + countBytes(string) + " bytes");
        countCharFrequency(string);
        toBinary(string);
        createHuffmanTree(string);
        
        charTable.write();
    }

    public void createHuffmanTree(String string) {
        countCharFrequency(string);
        toBinary(string);

        huffTree = new HuffmanTree(charTable);
        toCompressedCode(string);
    }

    public void countCharFrequency(String string) {
        for(int i = 0; i < string.length(); i++) {
            charTable.insertfrequency("" + string.charAt(i));
        }
    }

    public void toBinary(String string) {
        for(int i = 0; i < string.length(); i++) {
            charTable.insertBinaryASCII("" + string.charAt(i), "" + Integer.toBinaryString(string.charAt(i)));
        }
    }

    public void toCompressedCode(String string) {
        for(int i = 0; i < string.length(); i++) {
            charTable.insertCompression("" + string.charAt(i), huffTree.toCompressedCode("" + string.charAt(i), huffTree.root , ""));
        }
    }

    public String convertToBinary(String string) {
        String binary = "";
        for(int i = 0; i < string.length(); i++) {
            binary += charTable.convertToBinary("" + string.charAt(i));
        }
        return binary;        
    }
    public String convertToCompress(String string) {
        String binary = "";
        for(int i = 0; i < string.length(); i++) {
            binary += charTable.convertToCompress("" + string.charAt(i));
        }
        return binary;        
    }

    public int countBytes(String string) {
        int numberBytes = string.length();
        
        return numberBytes;
    }

}
