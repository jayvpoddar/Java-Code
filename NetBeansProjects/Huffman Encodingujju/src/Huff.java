
/**
 * Main/launch program for Huff assignment. A better
 * comment than this is warranted.
 * @author YOU THE STUDENT
 *
 */
public class Huff {

    public static void main(String[] args){
        HuffViewer sv = new HuffViewer("Duke Compsci Huffing");
        IHuffModel hm = new HuffModel();
        sv.setModel(hm);
    }
}
