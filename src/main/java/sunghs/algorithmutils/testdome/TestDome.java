package sunghs.algorithmutils.testdome;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

@Slf4j
public class TestDome {

    public static String[] uniqueNames(String[] names1, String[] names2) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        List<String> list1 = new ArrayList<>(Arrays.asList(names1));
        List<String> list2 = Arrays.asList(names2);
        list1.addAll(list2);
        return list1.stream().distinct().toArray(String[]::new);
    }

    /*public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", ElevenSt.uniqueNames(names1, names2)));
    }*/

    public static Roots findRoots(double a, double b, double c) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        double rootB = Math.sqrt((b * b) - (4 * a * c));
        double answerA = (-b + rootB) / (2 * a);
        double answerB = (-b - rootB) / (2 * a);
        return new Roots(answerA, answerB);
    }

    /*public static void main(String[] args) {
        Roots roots = ElevenSt.findRoots(2, 10, 8);
        System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
    }*/

    public static boolean contains(Node root, int value) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        while(root != null) {
            if(root.value == value) {
                return true;
            } else if (root.value < value) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return false;
    }

    /*public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }*/

    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("folder");

        List<String> result = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i ++) {
            String value = nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
            if(value.startsWith("" + startingLetter)) {
                result.add(value);
                System.out.println(nodeList.item(i).getNodeValue());
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                            "<folder name=\"program files\">" +
                                "<folder name=\"uninstall information\" />" +
                            "</folder>" +
                            "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)  System.out.println(name);
    }
}

class Roots {
    public final double x1, x2;

    public Roots(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}

class Node {
    public int value;
    public Node left, right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        Song root = this;
        Song current = this;
        Map<String, Boolean> songMap = new HashMap<>();
        songMap.put(current.name, true);

        while(current.nextSong != null) {
            current = current.nextSong;
            Boolean isContains = songMap.get(current.name);
            if(isContains != null && isContains) {
                return true;
            } else {
                songMap.put(current.name, true);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}

class UserInput {

    public static class TextInput {
        protected StringBuffer data;
        public TextInput() {
            data = new StringBuffer();
        }
        public void add(char c) {
            data.append(c);
        }
        public String getValue() {
            return this.data.toString();
        }
    }

    public static class NumericInput extends TextInput {
        public void add(char c) {
            int i = c - '0';
            if(i >= 0 && i <= 9) {
                super.data.append(i);
            }
        }
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5};
        list = Arrays.stream(list).filter(i -> i < 10).toArray();
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}