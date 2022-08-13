package com.example.serino

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        val cacheSizeBytes = Runtime.getRuntime().maxMemory() / 10
        builder.setMemoryCache(LruResourceCache(cacheSizeBytes))
        builder.setBitmapPool(LruBitmapPool(cacheSizeBytes))

        val diskCacheSizeBytes: Long = 1024 * 1024 * 1024 // 1GB
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, diskCacheSizeBytes))
    }
}