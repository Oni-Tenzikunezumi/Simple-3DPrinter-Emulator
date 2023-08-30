package simuRendering;

//エクストルーダーの動きのベクトルのクラス
class SimuVector {
    Point[] p = new Point[2];// 始点と終点
    boolean ext;// 材料の押出の有無

    SimuVector(Point p0, Point p1, boolean ext) {
        p[0] = p0;
        p[1] = p1;
        this.ext = ext;
    }
}
