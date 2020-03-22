package service

import (
	"bytes"
	"encoding/xml"
	"fmt"

	"github.com/cemayan/earthquake_collector/scraper/src/types/Earthquake"
	"golang.org/x/net/html/charset"
)

// Parse comment
func Parse(data []byte) []Earthquake.Earthquake {

	eqList := &Earthquake.Eqlist{}

	reader := bytes.NewReader(data)
	decoder := xml.NewDecoder(reader)
	decoder.CharsetReader = charset.NewReaderLabel
	err := decoder.Decode(&eqList)

	if err != nil {
		fmt.Errorf("there was an error decoding the type!")
	}

	return eqList.Eqlist
}
