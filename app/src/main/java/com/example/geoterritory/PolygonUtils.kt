package com.example.geoterritory

data class Point(val lat: Double, val lng: Double)

object PolygonUtils {

    fun isClosedLoop(points: List<Point>): Boolean {
        if (points.size < 4) return false

        val first = points.first()
        val last = points.last()

        val distance = haversine(first.lat, first.lng, last.lat, last.lng)

        return distance < 10  // meters
    }

    fun polygonArea(points: List<Point>): Double {
        var sum = 0.0
        for (i in points.indices) {
            val p1 = points[i]
            val p2 = points[(i + 1) % points.size]
            sum += (p1.lat * p2.lng - p2.lat * p1.lng)
        }
        return kotlin.math.abs(sum) / 2.0
    }

    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371000.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) *
                Math.sin(dLon / 2)

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    }
}
