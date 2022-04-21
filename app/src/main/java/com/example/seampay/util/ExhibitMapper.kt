package com.example.seampay.util


import com.example.seampay.db.Exhibits
import com.example.seampay.model.Exhibit
import com.example.seampay.model.ImagesList


class ExhibitMapper {
    companion object {


        fun mapToEntity(exhibitSeriesItems: List<Exhibit>): List<Exhibits> {

            val exhibitEntities = mutableListOf<Exhibits>()


            for  ( exhibitItemIndex  in exhibitSeriesItems.indices) {
                exhibitEntities.add(
                    Exhibits(
                        id = exhibitItemIndex,
                        title = exhibitSeriesItems[exhibitItemIndex].title,
                        images = ImagesList(exhibitSeriesItems[exhibitItemIndex].images)
                    )
                )
            }
            return exhibitEntities
        }
    }
}

