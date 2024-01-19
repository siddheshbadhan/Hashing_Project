/**
 * Author: sbadhan Siddhesh Badhan
 * Date Modified: 02/10/2023
 * The ComputeHashesModel class implements the functionality to hash a user input string using either MD5 or SHA-256 hashing.
 * */
package ds.project1task1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputeHashesModel {

    /**
     * This method takes an input string and returns an array with the original string,
     * the hexadecimal representation of the hashed string, and the Base 64 notation of the hashed string.
     *
     * @param inputStr The input string to be hashed.
     * @return An array of three elements with the original string, the hexadecimal representation, and the base 64 notation.
     */
    public String[] MD5Encrypt(String inputStr) {

        // An array to store the output string, hexadecimal representation, and base64 notation
        String[] op = new String[3];
        // Store the input string in the first element of the array
        op[0] = inputStr;
        // Try to compute the MD5 hash of the input string
        try {
            // Get a MessageDigest object for MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Convert the input string to bytes
            byte[] byteIp = inputStr.getBytes();
            // Compute the hash of the bytes
            byte[] byteOp = md.digest(byteIp);
            // Convert the hash bytes to hexadecimal representation
            op[1] = javax.xml.bind.DatatypeConverter.printHexBinary(byteOp);
            // Convert the hash bytes to base64 notation
            op[2] = javax.xml.bind.DatatypeConverter.printBase64Binary(byteOp);
        } catch (NoSuchAlgorithmException ex) {
            // Log the exception if the algorithm is not available
            Logger.getLogger(ComputeHashesModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the output array
        return op;
    }

    /**
     * This method takes an input string and returns an array with the original string,
     * the hexadecimal representation of the hashed string, and the Base 64 notation of the hashed string.
     *
     * @param inputStr The input string to be hashed.
     * @return An array of three elements with the original string, the hexadecimal representation, and the base 64 notation.
     */
    public String[] SHA256Encrypt(String inputStr) {
        // Create an array of 3 elements to store the results
        // output[0] = input string, output[1] = hexadecimal representation, output[2] = base64 notation
        String[] op = new String[3];
        // Store the input string in the first element of the array
        op[0] = inputStr;

        try {
            // Get the SHA-256 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Convert the input string to a byte array
            byte[] inputBytes = inputStr.getBytes();
            // Hash the input byte array and store the result in another byte array
            byte[] hashedBytes = md.digest(inputBytes);
            // Convert the hashed byte array to its hexadecimal representation
            op[1] = javax.xml.bind.DatatypeConverter.printHexBinary(hashedBytes);
            // Convert the hashed byte array to its base64 notation
            op[2] = javax.xml.bind.DatatypeConverter.printBase64Binary(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            // Log the exception if the SHA-256 algorithm is not available
            Logger.getLogger(ComputeHashesModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the array of results
        return op;
    }


}

