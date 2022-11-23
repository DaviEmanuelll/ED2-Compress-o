import java.util.ArrayList;
import java.util.List;

public class CharTable {
    List<CharacterAtributs> charList;
    int sizeList = 0;

    public CharTable() {
        this.charList = new ArrayList<CharacterAtributs>();
    } 
    
    //Escrever
    public void write() {
        for (CharacterAtributs characterAtributs : charList) {
            System.out.println(
                characterAtributs.getCharacter() + " - " + characterAtributs.getBinaryASCII() + "/" + characterAtributs.getCompression()
            );
        }
        System.out.println("-----------------------------------");
    }
    // Procurar
    public CharacterAtributs search(String searchChar) {
        for (CharacterAtributs findChar : charList) {
            if(findChar.getCharacter().equals(searchChar)) {
                return findChar;
            } 
        }

        return null;
    }
    // Trocar
    public void trade(int firstPosition, int secondPosition) {
        CharacterAtributs savecharacterAtributs = charList.get(firstPosition);
        
        charList.set(firstPosition, charList.get(secondPosition));
        charList.set(secondPosition, savecharacterAtributs);
    }
    // Organiza a lista de acordo com NÓS INTERNOS
    public void organizeHeap() {
        int lastPosition = sizeList - 1;
        for(int i = (lastPosition-1)/2; i >= 0; i--) {
            comeDown(i);
        }
    }
    // Subir
    public void rideUp(int indexPosition) {
        if(indexPosition>0) {
            int topPosition = (indexPosition-1) / 2;
            
            if(charList.get(topPosition).getFrequency()<charList.get(indexPosition).getFrequency()) {
                trade(topPosition, indexPosition);
            }
            rideUp(topPosition);
        }   
    }
     // Descer
     public void comeDown(int indexPosition) {
        if(sizeList > 1) {
            int lastPosition = sizeList - 1;
            int dawnPosition = indexPosition * 2 + 1;

            if(dawnPosition<=lastPosition) {
                if(dawnPosition<lastPosition) {
                    if(charList.get(dawnPosition+1).getFrequency()>charList.get(dawnPosition).getFrequency()) {
                        dawnPosition++;
                    }
                }
                if(charList.get(dawnPosition).getFrequency()>charList.get(indexPosition).getFrequency()) {
                    trade(indexPosition,dawnPosition);
                    comeDown(indexPosition);
                }
            }
        }
    }
    
    public String convertToBinary(String string) {
        CharacterAtributs characterAtributs = search(string);
        return characterAtributs.getBinaryASCII();
    }
    public String convertToCompress(String string) {
        CharacterAtributs characterAtributs = search(string);
        return characterAtributs.getCompression();
    }

    // Inserir
    public void insert(String character, int frequency, String binaryASCII, String compression) {
        CharacterAtributs newCharacterAtributs = new CharacterAtributs();
        if( search(character) != null) {
            newCharacterAtributs = search(character);
            newCharacterAtributs.setFrequency(frequency);
            newCharacterAtributs.setBinaryASCII(binaryASCII);
            newCharacterAtributs.setCompression(compression);
        } else {
            newCharacterAtributs.setCharacter(character);
            newCharacterAtributs.setFrequency(frequency);
            newCharacterAtributs.setBinaryASCII(binaryASCII);
            newCharacterAtributs.setCompression(compression);

            charList.add(newCharacterAtributs);
            rideUp(sizeList);
            sizeList++;
        }
        organizeHeap();
    }

    public void insertfrequency(String character) {
        CharacterAtributs newCharacterAtributs = new CharacterAtributs();
        if(search(character) != null) {
            newCharacterAtributs = search(character);
            newCharacterAtributs.setFrequency(newCharacterAtributs.getFrequency() + 1);
        } else {
            newCharacterAtributs.setCharacter(character);
            newCharacterAtributs.setFrequency(1);
            newCharacterAtributs.setBinaryASCII(null);
            newCharacterAtributs.setCompression(null);

            charList.add(newCharacterAtributs);
            rideUp(sizeList);
            sizeList++;
        }
        organizeHeap();
    }
    public void insertBinaryASCII(String character, String binaryASCII) {
        CharacterAtributs newCharacterAtributs = new CharacterAtributs();
        if(search(character) != null) {
            newCharacterAtributs = search(character);
            newCharacterAtributs.setBinaryASCII(binaryASCII);
        } else {
            newCharacterAtributs.setCharacter(character);
            newCharacterAtributs.setFrequency(1);
            newCharacterAtributs.setBinaryASCII(binaryASCII);
            newCharacterAtributs.setCompression(null);

            charList.add(newCharacterAtributs);
            rideUp(sizeList);
            sizeList++;
        }
        organizeHeap();
    }
    public void insertCompression(String character, String compression) {
        CharacterAtributs newCharacterAtributs = new CharacterAtributs();
        if(search(character) != null) {
            newCharacterAtributs = search(character);
            newCharacterAtributs.setCompression(compression);
        } else {
            newCharacterAtributs.setCharacter(character);
            newCharacterAtributs.setFrequency(1);
            newCharacterAtributs.setBinaryASCII(null);
            newCharacterAtributs.setCompression(compression);

            charList.add(newCharacterAtributs);
            rideUp(sizeList);
            sizeList++;
        }
        organizeHeap();
    }
   
    // Remover
    public CharacterAtributs remove() {
        if(sizeList>0){
            CharacterAtributs removed = charList.get(0);
            int lastPosition = sizeList - 1;

            for(int i = 0; i < lastPosition; i++){
                trade(i, i + 1);
            }
            
            charList.remove(lastPosition);
            sizeList--;
            organizeHeap();

            return removed;

        } else {
            System.out.println("Não existe processo para remover");
        }
        return null;
    }
}
