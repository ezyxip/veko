package com.ezyxip.veko.data

interface Identifiable<DataType> {
    val id: Int
    val data: DataType
}