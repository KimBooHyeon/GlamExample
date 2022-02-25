package com.cupist.glam.utils

import com.cupist.glam.network.model.Meta

class Constants {
    companion object {
        val VIEWTYPE_USER_CARD = 1
        val VIEWTYPE_PERSONALIZED_RECOMMEND = 2

        val DIALOG_TYPE_INTRODUCTION = 10
        val DIALOG_TYPE_HEIGHT = 11
        val DIALOG_TYPE_BODY_TYPE = 12
        val DIALOG_TYPE_JOB = 13
        val DIALOG_TYPE_EDUCATION = 14

        val DIALOG_LIST_JOB: ArrayList<Meta.KeyNamePair> = arrayListOf(
            Meta.KeyNamePair("개발자", "개발자"),
            Meta.KeyNamePair("디자이너", "디자이너"),
            Meta.KeyNamePair("기획자", "기획자 ")
        )
    }
}