public class HuffmanTree {
    Node root;

    public HuffmanTree(CharTable charTable) {
        Heap heapMin = new Heap();
        Node nodeController;
        for (CharacterAtributs character : charTable.charList) {
            nodeController = new Node();

            nodeController.setCharacter(character.getCharacter());
            nodeController.setFrequency(character.frequency);

            nodeController.setLeft(null);
            nodeController.setRight(null);

            heapMin.insert(nodeController);
        }

        root = null;

        while(heapMin.sizeList > 1) {
            Node firstMin = heapMin.remove();
            Node secondMin = heapMin.remove();

            Node tree = new Node();

            tree.setCharacter("-");
            tree.setFrequency(firstMin.getFrequency() + secondMin.getFrequency());

            tree.setLeft(firstMin);
            tree.setRight(secondMin);

            root = tree;
            heapMin.insert(tree);
        }
    }

    public void write(Node node, String code) {
        // Não entendi a necessidade do "&& isLetra(raiz.ch)" então resolvi não colocar
        if(node.getLeft() == null && node.getRight() == null) {
            System.out.println(node.getCharacter() + ": " + code);
            return;
        }
        write(node.getLeft(), code + "0");
        write(node.getRight(), code + "1");
        
    }

    public String toCompressedCode(String character, Node node, String code ) {
        if(node != null) {
            if(node.getCharacter().equals(character)) return code;

            String left = toCompressedCode(character, node.getLeft(), code + "0");
            String right = toCompressedCode(character, node.getRight(), code + "1");

            if(left != null) {
                return left;
            } 
            if(right != null) {
                return right;
            } 
        }
        return null;

    }
}