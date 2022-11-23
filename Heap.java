import java.util.ArrayList;
import java.util.List;

public class Heap {
    List<Node> nodeList;
    int sizeList = 0;

    public Heap() {
        this.nodeList = new ArrayList<Node>();
        sizeList = 0;
    } 
    // Trocar
    public void trade(int firstPosition, int secondPosition) {
        Node saveNode = nodeList.get(firstPosition);
        
        nodeList.set(firstPosition, nodeList.get(secondPosition));
        nodeList.set(secondPosition, saveNode);
    }
    //Organiza a lista de acordo com NÓS INTERNOS
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
            
            if(nodeList.get(topPosition).getFrequency()>nodeList.get(indexPosition).getFrequency()) {
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
                    if(nodeList.get(dawnPosition+1).getFrequency()<nodeList.get(dawnPosition).getFrequency()) {
                        dawnPosition++;
                    }
                }
                if(nodeList.get(dawnPosition).getFrequency()<nodeList.get(indexPosition).getFrequency()) {
                    trade(indexPosition,dawnPosition);
                    comeDown(indexPosition);
                }
            }
        }
    }
    // Inserir
    public void insert(Node newNode) {
        nodeList.add(newNode);
        rideUp(sizeList);
        sizeList++;
        organizeHeap();
    }
    // Remover
    public Node remove() {
        if(sizeList>0){
            Node removed = nodeList.get(0);
            int lastPosition = sizeList - 1;

            for(int i = 0; i < lastPosition; i++){
                trade(i, i + 1);
            }
            
            nodeList.remove(lastPosition);
            sizeList--;
            organizeHeap();

            return removed;

        } else {
            System.out.println("Não existe processo para remover");
        }
        return null;
    }
}
