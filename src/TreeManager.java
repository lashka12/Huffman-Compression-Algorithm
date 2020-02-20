
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The main class of this project
 */
public abstract class TreeManager {

	/**
	 * Global variables to be used by out script
	 */
	public static Map<String, String> encodeMap;
	public static String fileName = "src/Netflix.html";
	public static String text;
	public static String fileExt;

	public static void main(String[] args) throws IOException {

		/**
		 * Read the declared input file for encoding
		 */

		text = readFile(fileName, Charset.defaultCharset());
		fileExt = getFileExtension(fileName);

		/**
		 * Generate visitors & prioritized queue
		 */

		Queue<Node> queue = prioritizeQueue(generateVisitors());

		/**
		 * Convert the queue to huffman tree
		 */
		while (queue.size() > 1) {
			Node left = queue.poll();
			Node right = queue.poll();
			Visitor tmpVisitor = new Visitor(left.data._prob + right.data._prob, "__LEAF__");
			Node newNode = new Node(left, right, tmpVisitor);
			queue.add(newNode);
		}

		encodeMap = new HashMap<>();
		Node treeHead = queue.poll();

		/**
		 * Analyze the huffman tree to an encoded huffman tree hash map
		 */
		analyzeTree(treeHead, "");

		/**
		 * Encoding The Given File
		 */
		String encoded = encodeString(text);
		PrintWriter out = new PrintWriter(new FileWriter("output.enc")); // the compressed file
		out.print(encoded);
		out.close();

		/**
		 * Decode the encoded file that was generated
		 */
		String decoded = decodeString(treeHead, encoded);
		out = new PrintWriter(new FileWriter("output." + fileExt));
		out.print(decoded);
		out.close();

	}

	/**
	 * Saves a Huffman tree encoded hash map For use when decrypting a huffman
	 * string
	 * 
	 * @param n
	 * @param code
	 */

	public static void analyzeTree(Node n, String code) {
		if (n == null)
			return;

		if (n.isLeaf())
			encodeMap.put(n.data._data, code);

		analyzeTree(n._left, code + "0");
		analyzeTree(n._right, code + "1");

	}

	/**
	 * Generates a priority queue, ordered by usage numbers
	 * 
	 * @param list
	 * @return
	 */
	public static Queue<Node> prioritizeQueue(Map<String, Integer> list) {
		Queue<Node> toReturn = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.compareTo(o2);
			}
		});

		for (Map.Entry<String, Integer> entry : list.entrySet()) {
			toReturn.add(new Node(new Visitor(entry.getValue(), entry.getKey())));
		}

		return toReturn;
	}

	/**
	 * Generates visitors for the huffman queue
	 * 
	 * @return
	 */
	public static Map<String, Integer> generateVisitors() {

		Map<String, Integer> textToFreq = new HashMap<>();
		String[] splitted = text.split(" ");

		for (String s : splitted) {

			if (textToFreq.containsKey(s))
				textToFreq.put(s, textToFreq.get(s) + 1);

			else
				textToFreq.put(s, 1);
		}

		return textToFreq;
	}

	/**
	 * Encodes a string given an already generated huffman tree
	 * 
	 * @param n
	 * @param encode
	 * @return Encoded string, huffman tree bytes
	 */
	public static String encodeString(String encode) {

		String encoded_string = "";

		for (String s : encode.split(" ")) {
			encoded_string = encoded_string + encodeMap.get(s);

		}

		return encoded_string;
	}

	/**
	 * Decode a string using a pre-generated huffman tree
	 * 
	 * @param n
	 * @param encoded_string
	 * @return Original string, using the huffman tree
	 */
	public static String decodeString(Node n, String encoded_string) {

		String decoded = "";
		Node tmpNode = n;
		int i = 0;

		for (Character c : encoded_string.toCharArray()) {
			i++;
			if (c == '0')
				tmpNode = tmpNode._left;

			else
				tmpNode = tmpNode._right;

			if (tmpNode.isLeaf()) {

				decoded += tmpNode.data._data + " ";
				tmpNode = n;
			}

		}

		return decoded;
	}

	/**
	 * Reads a given file
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/**
	 * Returns the file extension
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String fileExt = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}
		return fileExt;
	}
}
