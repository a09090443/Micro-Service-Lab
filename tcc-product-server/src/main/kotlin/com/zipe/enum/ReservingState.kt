package com.zipe.enum

enum class ReservingState(val status: Int) {
    INVALID(Integer.MAX_VALUE),
    TRYING(0),
    CONFIRMED(1),
    CANCELLED(2)
}
