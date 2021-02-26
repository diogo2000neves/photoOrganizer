package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FrameGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton sortButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton selectButton;
    private JButton selectButton1;

    public FrameGUI(String title){
        selectButton.addActionListener(this);
        selectButton1.addActionListener(this);
        sortButton.addActionListener(this);


        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 400));
        this.setResizable(false);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    public void moveTo(String pathSource, String pathDestination, String fileName) throws IOException {
        Path sourceFile = Paths.get(pathSource);
        BasicFileAttributes attr = Files.readAttributes(sourceFile, BasicFileAttributes.class);
        //file's metadata
        String creationTime = String.valueOf(attr.lastModifiedTime()).substring(0, 10);
        String creationYear = creationTime.substring(0, 4), creationMonth = creationTime.substring(6, 7);
        String yearDir = pathDestination + "\\" +  creationYear;
        String monthDir = yearDir + "\\" + creationMonth;
        //check if year and month folder are created
        if(!Files.exists(Paths.get(yearDir)))
            Files.createDirectories(Paths.get(yearDir));
        if(!Files.exists(Paths.get(monthDir)))
            Files.createDirectories(Paths.get(monthDir));

        Path destinationFile = Paths.get(monthDir + "\\" + fileName);
        Path temp = Files.move(sourceFile, destinationFile);
        System.out.println("File moved to " + temp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //source button
        if(e.getSource() == selectButton){
            JFileChooser sourceFile = new JFileChooser();
            sourceFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = sourceFile.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(sourceFile.getSelectedFile().getAbsolutePath());
                textField1.setText(String.valueOf(file));
            }
        }
        //destination button
        else if(e.getSource() == selectButton1){
            JFileChooser destinationFile = new JFileChooser();
            destinationFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = destinationFile.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(destinationFile.getSelectedFile().getAbsolutePath());
                textField2.setText(String.valueOf(file));
            }
        }
        //sort button
        else if(e.getSource() == sortButton){
            String sourceDir = textField1.getText();
            String destinationDir = textField2.getText();

            File dir = new File(sourceDir);
            File[] fileList = dir.listFiles();

            if(!destinationDir.equals("")) {
                try {
                    if(fileList != null) {
                        int count = 0;
                        //iterate each file of the source
                        for(File child : fileList){
                            ++count;
                            moveTo(child.getAbsolutePath(), destinationDir, child.getName());
                        }
                        if(count == 0) System.out.println("Directory doesn't have files.");
                    } else {
                        System.out.println("You have to choose a source.");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else System.out.println("Invalid paths!");
        }
    }
}
