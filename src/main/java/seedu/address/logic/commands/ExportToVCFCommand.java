package seedu.address.logic.commands;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Exports listed persons in the address book to the file.
 */
public class ExportToVCFCommand extends Command {

    public static final String COMMAND_WORD = "exportVCF";
    public static final String COMMAND_ALIAS = "exVCF";

    public static final String MESSAGE_SUCCESS = "Exported listed persons to exportedVCF.csv";

    @Override
    public CommandResult execute() {

        try{
            File outputFile = new File("data/exportedFile/exportedVCF.csv");
            boolean created = outputFile.createNewFile();
            boolean writable = outputFile.canWrite();

            if(created){
                // new file created
                System.out.println("new file created");

            }else{
                // cannot create file
                // already exists
                System.out.println("unable to create file");
                // file will be overwritten
            }


            if(writable){
                // can write to file
                //System.out.println(outputFile.getPath());

                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getPath()));

                writer.write("Name,Phone,Email,Address"+System.getProperty("line.separator")); // headings

                //get data

                ObservableList<Person> peopleList= model.getFilteredPersonList();
                int numOfEntries = peopleList.size();
                String entryDetails;

                for(int currIdx = 0; currIdx < numOfEntries; currIdx++) {
                    Person currPerson = peopleList.get(currIdx);

                    entryDetails = "\""+currPerson.getName()+"\",\""+currPerson.getPhone()+"\",\""+currPerson.getEmail()+"\",\""+currPerson.getAddress()+"\"";
                    writer.append(entryDetails+System.getProperty("line.separator"));
                }

                writer.close();
            }else{
                // cannot write
                System.out.println("unable to write to file");
                //ERROR
            }

        }catch(IOException e) {
            System.out.println("TEST exception in ExportToVCFCommand");
            e.printStackTrace();
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
