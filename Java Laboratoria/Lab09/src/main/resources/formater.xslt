<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Karty informacyjne</title>
            </head>
            <body>
                <h1>Karty informacyjne</h1>
                <ul>
                    <xsl:apply-templates select="bip.poznan.pl/data/karty_informacyjne/items/karta_informacyjna"/>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="karta_informacyjna">
        <li>
            <a href="{link}"><xsl:value-of select="znak_sprawy"/></a>
        </li>
    </xsl:template>
</xsl:stylesheet>
