package com.example.testtask.core.retorift.deserializers

import java.util.Date

class ISODate(date: Date) : Date(date.time)