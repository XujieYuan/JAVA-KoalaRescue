public class Tree {

    private String treeType;
    private String usage;
    private double leaves;
    private int amount;

    public Tree() {
        treeType = "";
        usage = "";
        leaves = 0;
        amount = 0;
    }

    public Tree(String newTreeType, String newUsage, double newLeaves, int newAmount) {
        treeType = newTreeType;
        usage = newUsage;
        leaves = newLeaves;
        amount = newAmount;
    }

    public String getTreeType() {
        return treeType;
    }

    public String getUsage() {
        return usage;
    }

    public double getLeaves() {
        return leaves;
    }

    public int getAmount() {
        return amount;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setLeaves(double leaves) {
        this.leaves = leaves;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
