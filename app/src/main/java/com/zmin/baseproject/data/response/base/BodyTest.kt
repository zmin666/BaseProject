package com.zmin.baseproject.data.response.base

/**
 * @author: ZhangMin
 * @date: 2020/11/20 11:41
 * @version: 1.0
 * @desc:
 */
data class BodyTest(
    val error_message: String,
    val image_id: String,
    val request_id: String,
    val skeletons: List<Skeleton>,
    val time_used: Int
)

data class Skeleton(
    val body_rectangle: BodyRectangle,
    val landmark: Landmark
)

data class BodyRectangle(
    val height: Int,
    val left: Int,
    val top: Int,
    val width: Int
)

data class Landmark(
    val head: Head,
    val left_buttocks: LeftButtocks,
    val left_elbow: LeftElbow,
    val left_foot: LeftFoot,
    val left_hand: LeftHand,
    val left_knee: LeftKnee,
    val left_shoulder: LeftShoulder,
    val neck: Neck,
    val right_buttocks: RightButtocks,
    val right_elbow: RightElbow,
    val right_foot: RightFoot,
    val right_hand: RightHand,
    val right_knee: RightKnee,
    val right_shoulder: RightShoulder
)

data class Head(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftButtocks(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftElbow(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftFoot(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftHand(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftKnee(
    val score: Double,
    val x: Int,
    val y: Int
)

data class LeftShoulder(
    val score: Double,
    val x: Int,
    val y: Int
)

data class Neck(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightButtocks(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightElbow(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightFoot(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightHand(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightKnee(
    val score: Double,
    val x: Int,
    val y: Int
)

data class RightShoulder(
    val score: Double,
    val x: Int,
    val y: Int
)