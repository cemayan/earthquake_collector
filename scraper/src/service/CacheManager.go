package service

import (
	"log"
	"time"

	"github.com/allegro/bigcache"
)

var config = bigcache.Config{
	Shards:             1024,
	LifeWindow:         999999 * time.Minute,
	MaxEntriesInWindow: 1000 * 10 * 60,
	MaxEntrySize:       500,
	Verbose:            true,
	HardMaxCacheSize:   8192,
	OnRemove:           nil,
	OnRemoveWithReason: nil,
}

// InitCache comment
func InitCache() *bigcache.BigCache {
	cache, err := bigcache.NewBigCache(config)

	if err != nil {
		log.Fatal(err)
	}

	return cache
}
