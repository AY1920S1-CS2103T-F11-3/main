package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Dictionary;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.Analyser;
import seedu.address.model.password.analyser.DictionaryAnalyser;
import seedu.address.model.password.analyser.SequenceAnalyser;
import seedu.address.model.password.analyser.SimilarityAnalyser;
import seedu.address.model.password.analyser.StrengthAnalyser;
import seedu.address.model.password.analyser.UniqueAnalyser;

public class AnalyseStrongPasswordCommand extends AnalysePasswordCommand {
    private Index index;
    public static String MESSAGE_INIT = "----------------------------------------\n";

    public AnalyseStrongPasswordCommand(Index index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Password> passwordList = model.getFilteredPasswordList();
        Password passwordToAnalyse = passwordList.get(index.getZeroBased());
        List<Analyser> analyserList = getRequiredAnalysers();
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_INIT);
        reportBuilder.append(passwordToAnalyse);
        for (Analyser analyser : analyserList) {
            analyser.analyse(new ArrayList<Password>(Arrays.asList(passwordToAnalyse)));
            String report = analyser.outputDetailedReport();
            reportBuilder.append(report);
        }
        System.out.println(reportBuilder.toString());
        return new CommandResult("");
    }

    private List<Analyser> getRequiredAnalysers() {
        ArrayList<Analyser> analyserList = new ArrayList<>();
        analyserList.add(new UniqueAnalyser());
        //analyserList.add(new UserAsPassAnalyser());
        analyserList.add(new StrengthAnalyser());
        analyserList.add(new SimilarityAnalyser());
        analyserList.add(new DictionaryAnalyser(Dictionary.build(super.DICTIONARY_PASSWORD)));
        analyserList.add(new SequenceAnalyser());

        return analyserList;
    }

}
