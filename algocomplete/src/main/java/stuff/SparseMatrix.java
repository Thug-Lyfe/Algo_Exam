package stuff;

public class SparseMatrix {

    private int rowSize;
    private int columnSize;
    private SparseVector[] rows;
    private SparseVector[] columns;


    public SparseMatrix(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;

        rows = new SparseVector[rowSize];
        for(int i = 0; i < rows.length; i++) {
            rows[i] = new SparseVector(columnSize);
        }

        columns = new SparseVector[columnSize];
        for(int i = 0; i < columns.length; i++) {
            columns[i] = new SparseVector(rowSize);
        }
    }


    public void put(int row, int column, double value) {
        if (row < 0 || row >= rowSize) {
            throw new IllegalArgumentException("Invalid row index");
        }
        if (column < 0 || column >= columnSize) {
            throw new IllegalArgumentException("Invalid column index");
        }

        if (value == 0) {
            delete(row, column);
            return;
        }

        rows[row].put(column, value);
        columns[column].put(row, value);
    }

    public void delete(int row, int column) {
        if (row < 0 || row >= rowSize) {
            throw new IllegalArgumentException("Invalid row index");
        }
        if (column < 0 || column >= columnSize) {
            throw new IllegalArgumentException("Invalid column index");
        }

        rows[row].delete(column);
        columns[column].delete(row);
    }

    //Matrix-vector multiplication
    public SparseVector dot(SparseVector sparseVector) {
        if (columnSize != sparseVector.dimension) {
            throw new IllegalArgumentException("Matrix columns number and vector dimension must match");
        }

        SparseVector result = new SparseVector(rowSize);

        for(int i = 0; i < rows.length; i++) {
            double dot = rows[i].dot(sparseVector);

            if (dot != 0) {
                result.put(i, dot);
            }
        }
        return result;
    }


}