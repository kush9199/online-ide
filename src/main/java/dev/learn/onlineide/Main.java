import java.io.*;

public class Main {
    public static void main(String[] args) {
        String code = "\n#include<iostream>\n\nusing namespace std;\n\nint main(){\n    cout << \"Hoi\" << endl;\n    return 0;\n}\n";
//        try {
//            // Create a temporary Python file
//            File tempPythonFile = File.createTempFile("tempScript", ".py");
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPythonFile))) {
//                writer.write(code);
//            }
//
//            // Build the process to execute the Python file
//            ProcessBuilder processBuilder = new ProcessBuilder("python", tempPythonFile.getAbsolutePath());
//            processBuilder.redirectErrorStream(true); // Redirect errors to the same output stream
//
//            // Start the process
//            Process process = processBuilder.start();
//
//            // Capture and print the output
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//
//            // Wait for the process to complete
//            int exitCode = process.waitFor();
//            System.out.println("Process exited with code: " + exitCode);
//
//            // Delete the temporary file
//            tempPythonFile.deleteOnExit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            // Create a temporary Python file
            File tempPythonFile = File.createTempFile("tempScript", ".cpp");
            Process process = getProcess("g++",  code, tempPythonFile);

            StringBuffer output = new StringBuffer();
            // Capture and print the output
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
//            }

            // Wait for the process to complete
            int exitCode = process.waitFor();

            System.out.println(output.toString());
            System.out.println(exitCode);

            // Delete the temporary file
            tempPythonFile.deleteOnExit();

        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static Process getProcess(String command, String code, File tempPythonFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPythonFile))) {
            writer.write(code);
        }

        // Build the process to execute the Python file
        ProcessBuilder processBuilder = new ProcessBuilder(command, tempPythonFile.getAbsolutePath());
        processBuilder.redirectErrorStream(true); // Redirect errors to the same output stream

        // Start the process
        return processBuilder.start();
    }
}