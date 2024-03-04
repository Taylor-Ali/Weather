package com.leaf.repository.mapper

interface Mapper<DataClass, Model> {

    fun mapFromModel(type: Model?): DataClass?

    fun mapToModel(type: DataClass?): Model?
}