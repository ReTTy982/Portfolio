package ex.api;

public interface ClusterAnalysisService {
    public void setOptions(String[] options) throws ClusteringException; // ustawia opcje
    public String getName();
    public void submit(DataSet ds) throws ClusteringException;
    public DataSet retrieve(boolean clear) throws ClusteringException;
    public double getResult();
}

