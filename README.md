# Huffman-Compression-Algorithm
as a project during databases and algorithims course we were asked to imlpement the Huffman compression code using java 

Also known as Huffman encoding, an algorithm for the lossless compression of files based on the frequency of occurrence of 
a symbol in the file that is being compressed. The Huffman algorithm is based on statistical coding, which means that the 
probability of a symbol has a direct bearing on the length of its representation. The more probable the occurrence of a symbol is, 
the shorter will be its bit-size representation. In any file, certain characters are used more than others. Using binary 
epresentation, the number of bits required to represent each character depends upon the number of characters that have to be represented. 
Using one bit we can represent two characters, i.e., 0 represents the first character and 1 represents the second character. 
Using two bits we can represent four characters, and so on.

Unlike ASCII code, which is a fixed-length code using seven bits per character, Huffman compression is a variable-length coding 
system that assigns smaller codes for more frequently used characters and larger codes for less frequently used characters in order
to reduce the size of files being compressed and transferred.

For example, in a file with the following data:

XXXXXXYYYYZZ

the frequency of "X" is 6, the frequency of "Y" is 4, and the frequency of "Z" is 2. If each character is represented using 
a fixed-length code of two bits, then the number of bits required to store this file would be 24, 
i.e., (2 x 6) + (2x 4) + (2x 2) = 24.

If the above data were compressed using Huffman compression, the more frequently occurring numbers would be represented 
by smaller bits, such as:

X by the code 0 (1 bit)
Y by the code 10 (2 bits)
Z by the code 11 (2 bits)
therefore the size of the file becomes 18, i.e., (1x 6) + (2 x 4) + (2 x 2) = 18.

In the above example, more frequently occurring characters are assigned smaller codes, resulting in a smaller number of bits 
in the final compressed file.

Huffman compression was named after its discoverer, David Huffman.

you can replace the html file with any other file and try to compress it 
