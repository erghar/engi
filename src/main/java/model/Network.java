package model;

import daoImpl.DbCompany;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.util.*;

/**
 * Created by Marcin on 22/12/2015.
 */
public class Network {

    private Company selectedCompany;
    private Company randomCompany1;
    private Company randomCompany2;
    private Company randomCompany3;
    public double[] dividors = new double[4];

    public Network(Company selectedCompany, Company randomCompany1, Company randomCompany2, Company randomCompany3) {
        this.selectedCompany = selectedCompany;
        this.randomCompany1 = randomCompany1;
        this.randomCompany2 = randomCompany2;
        this.randomCompany3 = randomCompany3;
    }

    public Network() {
    }

    /**
     * Method to create two dimensional table from data of company what we want to create network and companies what
     * was randomly chosed to create associacions between.
     *
     * @param company
     * @param rndCompany
     * @param rnd2Company
     * @param rnd3Company
     * @return
     */
    public double[][] companyNormalize(Company company, Company rndCompany, Company rnd2Company, Company rnd3Company) {

        List<Double> listOfValues = new ArrayList<>();
        List<Double> listOfValues2 = new ArrayList<>();
        List<Double> listOfValues3 = new ArrayList<>();
        List<Double> listOfValues4 = new ArrayList<>();
        List<List<Double>> listOfListOfValues = new ArrayList<List<Double>>(4);

        List<CompanyExchange> listOfExchange = company.getListOfExchanges();
        List<CompanyExchange> listOfExchange2 = rndCompany.getListOfExchanges();
        List<CompanyExchange> listOfExchange3 = rnd2Company.getListOfExchanges();
        List<CompanyExchange> listOfExchange4 = rnd3Company.getListOfExchanges();

        double[][] tableOfValues = new double[4][];

        String helpV2 = "";
        try {
            for (int i = 0; i < listOfExchange.size(); i++) {
                helpV2 = listOfExchange.get(i).getValue().replace(",", ".");
                if (helpV2.contains(" ")) {
                    helpV2 = helpV2.replace(" ", "");
                }
                listOfValues.add(Double.parseDouble(helpV2));
            }

            for (int i = 0; i < listOfExchange2.size(); i++) {
                helpV2 = listOfExchange2.get(i).getValue().replace(",", ".");
                if (helpV2.contains(" ")) {
                    helpV2 = helpV2.replace(" ", "");
                }
                listOfValues2.add(Double.parseDouble(helpV2));
            }

            for (int i = 0; i < listOfExchange3.size(); i++) {
                helpV2 = listOfExchange3.get(i).getValue().replace(",", ".");
                if (helpV2.contains(" ")) {
                    helpV2 = helpV2.replace(" ", "");
                }
                listOfValues3.add(Double.parseDouble(helpV2));
            }

            for (int i = 0; i < listOfExchange4.size(); i++) {
                helpV2 = listOfExchange4.get(i).getValue().replace(",", ".");
                if (helpV2.contains(" ")) {
                    helpV2 = helpV2.replace(" ", "");
                }
                listOfValues4.add(Double.parseDouble(helpV2));
            }

            listOfListOfValues.add(listOfValues);
            listOfListOfValues.add(listOfValues2);
            listOfListOfValues.add(listOfValues3);
            listOfListOfValues.add(listOfValues4);

            dividors[0] = Collections.max(listOfValues);
            dividors[1] = Collections.max(listOfValues2);
            dividors[2] = Collections.max(listOfValues3);
            dividors[3] = Collections.max(listOfValues4);

            tableOfValues = new double[4][listOfValues.size()];
            for (int i = 0; i < listOfValues.size(); i++) {
                for (int j = 0; j < listOfListOfValues.size(); j++) {
                    tableOfValues[j][i] = listOfListOfValues.get(j).get(i) / dividors[j];
                }
            }

        } catch (Exception e) {
            System.err.print("There was problem please generate new network.");
        }

        return tableOfValues;

    }

    public double[] denormalize(double[] outputTable) {
        double[] output = new double[4];
        for (int i = 0; i < outputTable.length; i++) {
            output[i] = outputTable[i] * dividors[i];
        }
        return output;
    }

    /**
     * Method to prepeare dataset for prediction.
     *
     * @param tbToPrp1d
     * @return double[] preparedTable
     */
    public double[] prepareTables1d(double[][] tbToPrp1d) {
        try {
            double[] preparedTable = {tbToPrp1d[0][tbToPrp1d[0].length - 1], tbToPrp1d[1][tbToPrp1d[1].length - 1],
                    tbToPrp1d[2][tbToPrp1d[2].length - 1], tbToPrp1d[3][tbToPrp1d[3].length - 1]};

            return preparedTable;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "problem");
//            e.printStackTrace();
            System.err.println("Problem z prepareTable1d ");
            return null;
        }
    }

    /**
     * Method to prepeare one part of MLDataSet, input.
     *
     * @param tbToPrp
     * @return double[][] tbToPrp
     */
    public double[][] prepare2dTable(double[][] tbToPrp) {
        try {
            double preparedTable[][] = {{tbToPrp[0][tbToPrp[0].length - 5], tbToPrp[0][tbToPrp[0].length - 4], tbToPrp[0][tbToPrp[0].length - 3], tbToPrp[0][tbToPrp[0].length - 2]},
                    {tbToPrp[1][tbToPrp[1].length - 5], tbToPrp[1][tbToPrp[1].length - 4], tbToPrp[1][tbToPrp[1].length - 3], tbToPrp[1][tbToPrp[1].length - 2]},
                    {tbToPrp[2][tbToPrp[2].length - 5], tbToPrp[2][tbToPrp[2].length - 4], tbToPrp[2][tbToPrp[2].length - 3], tbToPrp[2][tbToPrp[2].length - 2]},
                    {tbToPrp[3][tbToPrp[3].length - 5], tbToPrp[3][tbToPrp[3].length - 4], tbToPrp[3][tbToPrp[3].length - 3], tbToPrp[3][tbToPrp[3].length - 2]}};

            return preparedTable;
        } catch (Exception e) {
            System.err.println("Problem z prepare2dTable ");
            return null;
        }

    }

    public double[][] prepare2dTbToPrpg(double[][] tbToPrp) {
        try {
            double preparedTable[][] = {{tbToPrp[0][tbToPrp[0].length - 4], tbToPrp[0][tbToPrp[0].length - 3], tbToPrp[0][tbToPrp[0].length - 2], tbToPrp[0][tbToPrp[0].length - 1]},
                    {tbToPrp[1][tbToPrp[1].length - 4], tbToPrp[1][tbToPrp[1].length - 3], tbToPrp[1][tbToPrp[1].length - 2], tbToPrp[1][tbToPrp[1].length - 1]},
                    {tbToPrp[2][tbToPrp[2].length - 4], tbToPrp[2][tbToPrp[2].length - 3], tbToPrp[2][tbToPrp[2].length - 2], tbToPrp[2][tbToPrp[2].length - 1]},
                    {tbToPrp[3][tbToPrp[3].length - 4], tbToPrp[3][tbToPrp[3].length - 3], tbToPrp[3][tbToPrp[3].length - 2], tbToPrp[3][tbToPrp[3].length - 1]}};

            return preparedTable;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Problem");
//            e.printStackTrace();
            System.err.println("Problem z prepare2dtbtoprpg");
            return null;
        }
    }

//    public double prepareIdealOutput(double[] selectedCompany, int day) {
//        if (selectedCompany.length - day + 1 > selectedCompany.length) {
//            return 0; // NARAZIE ZERO POTEM SIE POMYSLI
//        } else {
//            return selectedCompany[selectedCompany.length - day + 1];
//        }
//    }

    /**
     * Method to prepeare ideal output, second part of MLDataSet.
     *
     * @param tbToPrp
     * @return double[][] tbToPrp
     */
    public double[][] prepareIdealOutput(double[][] tbToPrp) {
        System.out.println(tbToPrp[0][tbToPrp.length]);
        double preparedTable[][] = {{tbToPrp[0][tbToPrp[0].length - 1]},
                {tbToPrp[0][tbToPrp[0].length - 1]},
                {tbToPrp[0][tbToPrp[0].length - 1]},
                {tbToPrp[0][tbToPrp[0].length - 1]}};
        return preparedTable;
    }

    /**
     * Method to create neural network.
     *
     * @param input
     * @param ideal
     * @param companyName
     */
    public void createAndTrainNetwork(double[][] input, double[][] ideal, String companyName) {
        BasicNetwork network2 = new BasicNetwork();
        network2.addLayer(new BasicLayer(null, true, 4));
        network2.addLayer(new BasicLayer(new ActivationSigmoid(), true, 8));
        network2.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));

        network2.getStructure().finalizeStructure();
        network2.reset();


        MLDataSet trainingSet = new BasicMLDataSet(input, ideal);

        double[] helpArray = dividors;
        double[] error = new double[4];
        int z = 0;
        String log = "";
        log = trainNetwork(network2, trainingSet);
        String[] networkOutput = new String[4];

        for (MLDataPair pair : trainingSet) {
            final MLData output = network2.compute(pair.getInput());
            networkOutput[z] = ("Yesterday " + String.format(String.format("%.04f", pair.getInput().getData(3) * helpArray[z]) +
                    " predicted = " + String.format("%.04f", output.getData(0) * helpArray[z]) +
                    " correct = " + String.format("%.04f", pair.getIdeal().getData(0) * helpArray[z]) + " difference = " + String.format("%.04f", (output.getData(0) * helpArray[z] - pair.getIdeal().getData(0) * helpArray[z])) + "\n"));
            z++;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Network generated!");
        alert.setHeaderText("Please take a look for your options");
        alert.setContentText("Choose your option.");

        Label label = new Label("Network output:");
        log += "------------------------------------------------\n";
        log += networkOutput[0];
        TextArea textArea = new TextArea(log);
        textArea.setEditable(false);
        textArea.setWrapText(false);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        ButtonType buttonTypeOne = new ButtonType("Save network");
        ButtonType buttonTypeTwo = new ButtonType("Try again");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            EncogDirectoryPersistence.saveObject(new File(companyName + " network.eg"), network2);
            alert.close();
        } else if (result.get() == buttonTypeTwo) {
            //TODO make btn generate fire().
            alert.close();
        } else {
            alert.close();
        }
        Encog.getInstance().shutdown();

    }


    /**
     * Method to train or retrain existing network.
     *
     * @param newNetwork
     * @param trainingSet
     * @return
     */
    public static double[] trainNewNetwork(BasicNetwork newNetwork, MLDataSet trainingSet) {

        final Backpropagation train = new Backpropagation(newNetwork, trainingSet, 0.7, 0.3);
        //Backpropagation train = new Backpropagation(newNetwork, trainingSet);
        int epoch = 1;

        do {
            train.iteration();
            System.out.println("Epoch: " + epoch + " Error: "
                    + train.getError() + "\n");
            epoch++;
        } while (train.getError() > 0.0001);

        double[] newNetworkError = new double[4];
        return newNetworkError;
    }

    public static String trainNetwork(BasicNetwork newNetwork, MLDataSet trainingSet) {

        //final ResilientPropagation train = new ResilientPropagation(newNetwork, trainingSet);
        final Backpropagation train = new Backpropagation(newNetwork, trainingSet, 0.7, 0.3);
        int epoch = 1;

        String log = "";
        do {
            train.iteration();
            log += "Epoch: " + epoch + " Error: "
                    + train.getError() + "\n";
            epoch++;
        } while (train.getError() > 0.0001);

        // double[] newNetworkError = new double[4];
        return log;
    }

    public BasicNetwork loadEgsistingNetwork(String companyName) {
        BasicNetwork network = (BasicNetwork) EncogDirectoryPersistence.loadObject(
                new File(companyName + " network.eg"));
        return network;
    }

    public void loadAndEvaluate(MLData trainingSet, Company company) {
        int z = 0;
        int j = 0;

        BasicNetwork network = (BasicNetwork) EncogDirectoryPersistence.loadObject(
                new File(company.getCompanyName() + " network.eg"));

        MLData output = network.compute(trainingSet);
        double value = output.getData(0) * dividors[0];
        //System.out.println(value * dividors[0] +
        double[] values = new double[100];
        for (int i = 0; i < 100; i++) {
            values[i] = network.compute(trainingSet).getData(0) * dividors[0];
        }
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        double mean = (sum / values.length);
        PredictedExchange predictedExchange = new PredictedExchange();
        predictedExchange.setCompany(company);


        predictedExchange.setPredictedValue(String.valueOf(roundOff(value, 2)));
        predictedExchange.setPredictedValueMean(String.valueOf(roundOff(mean, 2)));

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.FRIDAY) { // If it's Friday so skip to Monday
            c.add(Calendar.DATE, 3);
        } else if (dayOfWeek == Calendar.SATURDAY) { // If it's Saturday skip to Monday
            c.add(Calendar.DATE, 2);
        } else {
            c.add(Calendar.DATE, 1);
        }
        dt = c.getTime();

        predictedExchange.setDate(dt);

        DbCompany dbCompany = new DbCompany();
        dbCompany.savePredictedCompanyExchange(predictedExchange);
    }

    static double roundOff(double x, int position) {
        double a = x;
        double temp = Math.pow(10.0, position);
        a *= temp;
        a = Math.round(a);
        return (a / (temp));
    }
}