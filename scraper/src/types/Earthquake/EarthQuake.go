package Earthquake

import "encoding/xml"

type Eqlist struct {
	XMLName xml.Name     `xml:"eqlist"  json:"eqlist"`
	Eqlist  []Earthquake `xml:"earhquake" json:"earhquake" `
}

type Earthquake struct {
	Name     string `xml:"name,attr" json:"name"`
	Lokasyon string `xml:"lokasyon,attr" json:"lokasyon"`
	Lat      string `xml:"lat,attr" json:"lat"`
	Lng      string `xml:"lng,attr" json:"lng"`
	Mag      string `xml:"mag,attr" json:"mag"`
	Depth    string `xml:"Depth,attr" json:"Depth"`
}
