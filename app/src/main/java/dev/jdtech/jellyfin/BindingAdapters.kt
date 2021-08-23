package dev.jdtech.jellyfin

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.jdtech.jellyfin.adapters.*
import dev.jdtech.jellyfin.api.JellyfinApi
import dev.jdtech.jellyfin.database.Server
import dev.jdtech.jellyfin.models.FavoriteSection
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemPerson
import java.util.*

@BindingAdapter("servers")
fun bindServers(recyclerView: RecyclerView, data: List<Server>?) {
    val adapter = recyclerView.adapter as ServerGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("views")
fun bindViews(recyclerView: RecyclerView, data: List<HomeItem>?) {
    val adapter = recyclerView.adapter as ViewListAdapter
    adapter.submitList(data)
}

@BindingAdapter("items")
fun bindItems(recyclerView: RecyclerView, data: List<BaseItemDto>?) {
    val adapter = recyclerView.adapter as ViewItemListAdapter
    adapter.submitList(data)
}

@BindingAdapter("itemImage")
fun bindItemImage(imageView: ImageView, item: BaseItemDto) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    val itemId = if (item.type == "Episode") item.seriesId else item.id

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${itemId}/Images/Primary"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.neutral_800)
        .into(imageView)

    imageView.contentDescription = "${item.name} poster"
}

@BindingAdapter("itemBackdropImage")
fun bindItemBackdropImage(imageView: ImageView, item: BaseItemDto?) {
    if (item != null) {
        val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

        Glide
            .with(imageView.context)
            .load(jellyfinApi.api.baseUrl.plus("/items/${item.id}/Images/Backdrop"))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        imageView.contentDescription = "${item.name} backdrop"
    }
}

@BindingAdapter("itemBackdropById")
fun bindItemBackdropById(imageView: ImageView, itemId: UUID) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${itemId}/Images/Backdrop"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("collections")
fun bindCollections(recyclerView: RecyclerView, data: List<BaseItemDto>?) {
    val adapter = recyclerView.adapter as CollectionListAdapter
    adapter.submitList(data)
}

@BindingAdapter("collectionImage")
fun bindCollectionImage(imageView: ImageView, item: BaseItemDto) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${item.id}/Images/Primary"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.neutral_800)
        .into(imageView)

    imageView.contentDescription = "${item.name} image"
}

@BindingAdapter("people")
fun bindPeople(recyclerView: RecyclerView, data: List<BaseItemPerson>?) {
    val adapter = recyclerView.adapter as PersonListAdapter
    adapter.submitList(data)
}

@BindingAdapter("personImage")
fun bindPersonImage(imageView: ImageView, person: BaseItemPerson) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${person.id}/Images/Primary"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.neutral_800)
        .into(imageView)

    imageView.contentDescription = "${person.name} poster"
}

@BindingAdapter("episodes")
fun bindEpisodes(recyclerView: RecyclerView, data: List<EpisodeItem>?) {
    val adapter = recyclerView.adapter as EpisodeListAdapter
    adapter.submitList(data)
}

@BindingAdapter("homeEpisodes")
fun bindHomeEpisodes(recyclerView: RecyclerView, data: List<BaseItemDto>?) {
    val adapter = recyclerView.adapter as HomeEpisodeListAdapter
    adapter.submitList(data)
}

@BindingAdapter("episodeImage")
fun bindEpisodeImage(imageView: ImageView, episode: BaseItemDto) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    val imageType = if (episode.type == "Movie") "Backdrop" else "Primary"

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${episode.id}/Images/$imageType"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.neutral_800)
        .into(imageView)

    imageView.contentDescription = "${episode.name} poster"
}

@BindingAdapter("seasonPoster")
fun bindSeasonPoster(imageView: ImageView, seasonId: UUID) {
    val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

    Glide
        .with(imageView.context)
        .load(jellyfinApi.api.baseUrl.plus("/items/${seasonId}/Images/Primary"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.neutral_800)
        .into(imageView)
}

@BindingAdapter("itemPrimaryImage")
fun bindItemPrimaryImage(imageView: ImageView, item: BaseItemDto?) {
    if (item != null) {
        val jellyfinApi = JellyfinApi.getInstance(imageView.context.applicationContext, "")

        Glide
            .with(imageView.context)
            .load(jellyfinApi.api.baseUrl.plus("/items/${item.id}/Images/Primary"))
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.neutral_800)
            .into(imageView)

        imageView.contentDescription = "${item.name} poster"
    }
}

@BindingAdapter("favoriteSections")
fun bindFavoriteSections(recyclerView: RecyclerView, data: List<FavoriteSection>?) {
    val adapter = recyclerView.adapter as FavoritesListAdapter
    adapter.submitList(data)
}