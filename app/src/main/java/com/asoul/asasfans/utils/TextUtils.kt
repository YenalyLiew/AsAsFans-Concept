package com.asoul.asasfans.utils

fun Int.toViewsCase(): String {
    if (this / 1_0000 == 0) {
        return this.toString()
    }
    if (this / 1_0000 < 10) {
        return this.toString().substring(0, 1) + "\\." + this.toString().substring(1, 2) + "万"
    }
    if (this / 10_0000 < 10) {
        return this.toString().substring(0, 2) + "\\." + this.toString().substring(2, 3) + "万"
    }
    if (this / 100_0000 < 10) {
        return this.toString().substring(0, 3) + "\\." + this.toString().substring(3, 4) + "万"
    }
    if (this / 1000_0000 < 10) {
        return this.toString().substring(0, 4) + "\\." + this.toString().substring(4, 5) + "万"
    }
    if (this / 1_0000_0000 < 10) {
        return this.toString().substring(0, 1) + "\\." + this.toString().substring(1, 2) + "亿"
    }
    if (this / 10_0000_0000 < 10) {
        return this.toString().substring(0, 2) + "\\." + this.toString().substring(2, 3) + "亿"
    }
    return this.toString()
}