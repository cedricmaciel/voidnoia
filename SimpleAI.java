import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class SimpleAI {

    private MultiLayerNetwork model;

    public SimpleAI() {
        int inputSize = 784;  //pixels  imagem
        int outputSize = 10;  

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .updater(new Sgd(0.1))
                .list()
                .layer(new DenseLayer.Builder().nIn(inputSize).nOut(1000)
                        .activation(Activation.RELU).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(1000).nOut(outputSize).build())
                .build();

        model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));
    }

    public void train(DataSet trainingData) {
        model.fit(trainingData);
    }

    public int[] predict(DataSet testData) {
        return model.predict(testData);
    }

    public static void main(String[] args) {
        SimpleAI ai = new SimpleAI();
        //  dados e treinar sua IA
        
    }
}
