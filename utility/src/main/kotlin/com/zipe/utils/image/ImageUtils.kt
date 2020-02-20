package com.zipe.utils.image

object ImageUtils {

    /**
     * 幾種常見的圖片格式
     */
    @JvmStatic
    val IMAGE_TYPE_GIF = "gif" // 圖形交換格式

    @JvmStatic
    val IMAGE_TYPE_JPG = "jpg" // 聯合照片專家組

    @JvmStatic
    val IMAGE_TYPE_JPEG = "jpeg" // 聯合照片專家組

    @JvmStatic
    val IMAGE_TYPE_BMP = "bmp" // 英文Bitmap（位圖）的簡寫，它是Windows操作系統中的標准圖像文件格式

    @JvmStatic
    val IMAGE_TYPE_PNG = "png" // 可移植網络圖形

    @JvmStatic
    val IMAGE_TYPE_PSD = "psd" // Photoshop的專用格式Photoshop

    @JvmStatic
    val IMAGE_TYPE_UNKNOWN = "unknown" // 未知圖片格式

    @JvmStatic
    fun scale(srcImageFile: String, result: String, scale: Int, flag: Boolean) {

    }
}
