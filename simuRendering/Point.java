package simuRendering;

//エクストルーダーの座標のベクトルのクラス
class Point {
    double x, y, z, e;// エクストルーダーの座標と押出量
    double rx, ry, rz; // 回転させた後の座標
    int screenX, screenY; // スクリーン上の座標

    Point(double x, double y, double z, double e) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.e = e;
    }
}
