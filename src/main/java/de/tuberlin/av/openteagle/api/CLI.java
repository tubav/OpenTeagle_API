package de.tuberlin.av.openteagle.api;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import de.tuberlin.av.openteagle.exceptions.VCTNotFoundException;
import de.tuberlin.av.openteagle.model.vct.VctPrinter;
import de.tuberlin.av.openteagle.model.vct.jaxb.Vct;
import de.tuberlin.av.openteagle.utils.TeagleProperties;

public class CLI {

	@Parameter(names = "--repourl", description = "The repository URL")
	private final transient String repoUrl = TeagleProperties.getRepoUrl();

	private static final String CMD_LST_VCT = "listVCTs";
	private final transient CommandListVCTs commandListVCTs = new CommandListVCTs();
	private static final String CMD_SHW_VCT = "showVCT";
	private final transient CommandShowVCT commandShowVCT = new CommandShowVCT();

	public static void main(final String[] args) {
		System.out.println(new CLI().parse(args));
	}

	public String parse(final String[] args) {
		final JCommander parameter = new JCommander(this);
		parameter.setProgramName("OpenTeagleCLI");
		parameter.addCommand(CLI.CMD_LST_VCT, this.commandListVCTs);
		parameter.addCommand(CLI.CMD_SHW_VCT, this.commandShowVCT);
		String result = "";

		try {
			parameter.parse(args);
			final String command = parameter.getParsedCommand();
			if (CLI.CMD_LST_VCT.equals(command)) {
				result = this.commandListVCTs.exec(this.repoUrl);
			} else if (CLI.CMD_SHW_VCT.equals(command)) {
				result = this.commandShowVCT.exec(this.repoUrl);

			} else {
				result = CLI.getUsage(parameter);
			}
		} catch (final Exception ex) {
			result = "ERROR: " + ex.getMessage() + "\n";
			result += CLI.getUsage(parameter);
		}

		return result;
	}

	private static String getUsage(final JCommander parameter) {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(stream);
		final PrintStream stdout = System.out;
		final PrintStream stderr = System.err;

		System.setOut(printStream);
		System.setErr(printStream);
		parameter.usage();
		System.setOut(stdout);
		System.setErr(stderr);

		return stream.toString();
	}

	@Parameters(separators = "=", commandDescription = "List all current VCTs in the repository")
	private class CommandListVCTs {

		public String exec(final String url) {
			final OpenTeagleAPI openteagle = new OpenTeagleAPI(url);
			final List<Vct> availableVCTs = openteagle.getListOfVCTs();
			String result = "";

			for (final Vct vct : availableVCTs) {
				result += VctPrinter.printSummary(vct);
			}

			return result;
		}
	}

	@Parameters(separators = "=", commandDescription = "List details about a specific VCT")
	private class CommandShowVCT {
		@Parameter(names = "--vctID", description = "The repository URL", required = true)
		private String vctID = "";

		public String exec(final String url) {
			final OpenTeagleAPI openteagle = new OpenTeagleAPI(url);
			try {
				return VctPrinter.printDetails(openteagle.getVCT(this.vctID));
			} catch (VCTNotFoundException e) {
				return " * ID: '" + this.vctID + "' (not found)";
			}
		}
	}
}