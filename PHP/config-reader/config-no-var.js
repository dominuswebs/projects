{
    settings: {
        daypart: "day",
        orientation: "portrait",
    },
    content: [
        // non rotating content
        // background
        {
            module: "moduleImage",
            data: {
                name: "5PP1-bg",
                folder: "library-FLG26/backgrounds/",
                classes: "background"
            }
        },

        //legal
        {
            module: "groupContainer",
            data: {
                styles: {
                    top: 1620,
                    left: 50
                },
                classes: "grid cols-2",
                modules: [
                    {
                        module: "textGeneric",
                        data: {
                            text: "The average adult daily energy intake is 8700",
                            classes: "kc-light-italic",
                            overrides: {
                                "font-size": "48px",
                                "font-weight": 300,
                                "line-height": "90%",
                                "letter-spacing": "-1.4px"
                            }
                        }
                    },
                    {
                        module: "textGeneric",
                        data: {
                            text: "kJ",
                            classes: "kc-light-italic",
                            overrides: {
                                "font-size": "36px",
                                "font-weight": 300,
                                "line-height": "90%"
                            }
                        }
                    },
                ]
            }
        },
        // ALC headline
        {
            module: "textGeneric",
            data: {
                styles: {
                    top: 1695,
                    left: 70,
                    width: 800
                },
                text: "Make it a combo or box",
                classes: "name red",
                overrides: {
                    "font-size": "35px"
                }
            }
        },
        // Swap to a kwench / Go large
        {
            module: "moduleIf",
            data: {
                type: "anyTag",
                condition: ["kwench_condition", "kwench_national_condition"],
                ifTrue: [
                    //swap to kwench
                    {
                        module: "groupContainer",
                        data: {
                            styles: {
                                top: 1700,
                                left: 735,
                                width: 200
                            },
                            modules: [
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 156,
                                            left: 20,
                                            width: 200
                                        },
                                        classes: "grid cols-1 gap-5",
                                        modules: [
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-2 gap-1",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                overrides: {
                                                                    "font-size": "34px"
                                                                },
                                                                text: "+",
                                                                classes: "name red",
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                overrides: {
                                                                    "font-size": "34px"
                                                                },
                                                                code: "NA002",
                                                                classes: "price red"
                                                            },
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "textGeneric",
                                                data: {
                                                    text: "swap to a",
                                                    classes: "name red",
                                                    overrides: {
                                                        "font-size": "16px"
                                                    },
                                                }
                                            },
                                        ]
                                    }
                                },
                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "5PP-kwench-call-out",
                                        folder: "library-FLG26/components/",
                                        classes: "",
                                        styles: {
                                            top: 0,
                                            left: 0,
                                            scale: 1,
                                            layer: 90
                                        },
                                    }
                                },
                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "pepsi-can",
                                        folder: "library-FLG26/drinks/",
                                        classes: "",
                                        styles: {
                                            top: -205,
                                            left: -228,
                                            scale: 0.27,
                                            layer: 100
                                        },
                                    }
                                },
                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "Kwench-Caramel-Krunch-Shake",
                                        folder: "library-FLG26/kwench/",
                                        classes: "",
                                        styles: {
                                            top: -543,
                                            left: -395,
                                            scale: 0.21,
                                            layer: 100
                                        },
                                    }
                                }
                            ]
                        }
                    },
                ],
                ifFalse: [
                    // Go large
                    {
                        module: "groupContainer",
                        data: {
                            styles: {
                                top: 1731,
                                left: 764,
                                width: 450
                            },
                            modules: [
                                {
                                    module: "groupContainer",
                                    data: {
                                        classes: "grid cols-1 gap-10 bg-red product-callout border-25",
                                        styles: {
                                            padding: [20, 32, 62, 32],
                                            radius: [15, 15, 0, 0],
                                            width: 231
                                        },
                                        modules: [
                                            {
                                                module: "textGeneric",
                                                data: {
                                                    text: "Go<br>Large",
                                                    classes: "name lg white",
                                                    overrides: {
                                                        "font-size": "45px",
                                                        "letter-spacing": "-2px"
                                                    }
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    styles: { top: 1 },
                                                    classes: "grid cols-2 gap-0",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "+",
                                                                classes: "name sm white",
                                                                overrides: {
                                                                    "font-size": "23px"
                                                                }
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA002",
                                                                classes: "price white sm",
                                                                overrides: {
                                                                    "font-size": "23px"
                                                                }
                                                            },
                                                        },
                                                    ]
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    styles: { top: 0 },
                                                    classes: "grid cols-2 gap-0",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "+",
                                                                classes: "energy sm white",
                                                                overrides: {
                                                                    "font-size": "23px"
                                                                }
                                                            }
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {

                                                                code: "NA002",
                                                                classes: "energy white sm",
                                                                overrides: {
                                                                    "font-size": "23px"
                                                                }
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                        ]
                                    }
                                },
                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "go-large",
                                        folder: "library-FLG26/sides/",
                                        classes: "",
                                        styles: {
                                            top: -521,
                                            left: -465,
                                            scale: 0.25,
                                            layer: 110
                                        },
                                    }
                                }
                            ]
                        }
                    },
                ]
            }
        },
        // rotating content
        // burgers
        {
            module: "moduleIf",
            data: {
                type: "anyTag",
                condition: ["hide_burger_section"],
                ifTrue: [],
                ifFalse: [
                    {
                        module: "sectionContainer",
                        data: {
                            classes: "burger-section",
                            overrides: {
                                "z-index": "unset"
                            },
                            modules: [

                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "5PP-burgers-hl",
                                        folder: "library-FLG26/components/",
                                    }
                                },
                                {
                                    module: "textGeneric",
                                    data: {
                                        styles: {
                                            top: 30,
                                            left: 240
                                        },
                                        text: "combos",
                                        classes: "headline",

                                    },
                                },
                                {
                                    module: "textGeneric",
                                    data: {
                                        styles: {
                                            top: 30,
                                            left: 830
                                        },
                                        text: "Incl. Reg. Chips and<br>Reg. Pepsi Max",
                                        classes: "subheadline",
                                        overrides: {
                                            "line-height": "130%"
                                        }

                                    },
                                },
                                // Hero
                                // OC combo
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 168,
                                            left: 293,
                                            width: 450
                                        },
                                        modules: [
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10 bg-green product-callout border-25",
                                                    styles: {
                                                        padding: [30, 50],
                                                    },
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "original crispy<br>burger combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "groupContainer",
                                                            data: {
                                                                classes: "grid cols-2 gap-5",
                                                                modules: [
                                                                    {
                                                                        module: "textPriceFormat",
                                                                        data: {

                                                                            code: "NA105",
                                                                            classes: "price red lg"
                                                                        },
                                                                    },
                                                                    {
                                                                        module: "textEnergyFormat",
                                                                        data: {

                                                                            code: "NA105",
                                                                            classes: "energy lg"
                                                                        }
                                                                    }
                                                                ]
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "cup-pepsi-combo",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -140,
                                                                    left: -510,
                                                                    scale: 0.74,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "can-pepsi-combo-v2",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -140,
                                                                    left: -280,
                                                                    scale: 0.74,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "original-crispy-burger",
                                                    folder: "library-FLG26/burgers/",
                                                    classes: "",
                                                    styles: {
                                                        top: -70,
                                                        left: -310,
                                                        scale: 0.70,
                                                        layer: 105
                                                    },
                                                }
                                            },
                                            {
                                                module: "textPriceFormat",
                                                data: {
                                                    code: "NA105",
                                                    classes: "price white bg-red border-15 callout",
                                                    styles: {
                                                        padding: [20],
                                                        left: 435,
                                                        top: 320,
                                                        layer: 104
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                                // combos
                                // zinger combo
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1413,
                                            left: 180,
                                            width: 200
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        left: -25,
                                                        top: 1,
                                                        scale: 1,
                                                        layer: 300
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "zinger<span class='super'>&reg;</span><br>combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "I-30691-0",
                                                                classes: "price red lg"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "I-30691-0",
                                                                classes: "energy lg"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "cup-pepsi-combo",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -840,
                                                                    left: -677,
                                                                    scale: 0.40,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "can-pepsi-combo-v2",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -766,
                                                                    left: -497,
                                                                    scale: 0.45,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "zinger-burger",
                                                    folder: "library-FLG26/burgers/",
                                                    classes: "",
                                                    styles: {
                                                        top: -801,
                                                        left: -511,
                                                        scale: 0.405,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },

                                // original crunch twister
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1413,
                                            left: 550,
                                            width: 600
                                        },
                                        modules: [
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "original crunch<BR>twister<span class='super'>&reg;</span> combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "I-31898-0",
                                                                classes: "price red lg"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "I-31898-0",
                                                                classes: "energy lg"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "cup-pepsi-combo",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -840,
                                                                    left: -652,
                                                                    scale: 0.40,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "can-pepsi-combo-v2",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -766,
                                                                    left: -375,
                                                                    scale: 0.45,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "original-crunch-twister",
                                                    folder: "library-FLG26/twisters/",
                                                    classes: "",
                                                    styles: {
                                                        top: -755,
                                                        left: -349,
                                                        scale: 0.375,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                                // ALC

                                // zinger
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1789,
                                            left: 257,
                                            width: 200
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: -7,
                                                        left: -20,
                                                        scale: 0.6,
                                                        layer: 300
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-5",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "zinger<span class='super'>&reg;</span>",
                                                                classes: "name sm",
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA120",
                                                                classes: "price sm red",
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "NA120",
                                                                classes: "energy sm"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "zinger-burger",
                                                    folder: "library-FLG26/burgers/",
                                                    classes: "",
                                                    styles: {
                                                        top: -579,
                                                        left: -732,
                                                        scale: 0.21,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },

                                // zinger crunch
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1789,
                                            left: 582,
                                            width: 200
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: -7,
                                                        left: -20,
                                                        scale: 0.6,
                                                        layer: 300
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-5",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "zinger<span class='super'>&reg;</span> crunch",
                                                                classes: "name sm",
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA121",
                                                                classes: "price sm red",
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "NA121",
                                                                classes: "energy sm"
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "zinger-crunch-burger",
                                                    folder: "library-FLG26/burgers/",
                                                    classes: "",
                                                    styles: {
                                                        top: -580,
                                                        left: -723,
                                                        scale: 0.212,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                            ]
                        }
                    }
                ]
            }
        },
        // chicken
        {
            module: "moduleIf",
            data: {
                type: "anyTag",
                condition: ["hide_chicken_section"],
                ifTrue: [],
                ifFalse: [
                    {
                        module: "sectionContainer",
                        data: {
                            classes: "chicken-section",
                            overrides: {
                                "z-index": "unset"
                            },
                            modules: [
                                {
                                    module: "moduleImage",
                                    data: {
                                        name: "5PP-chicken-hl",
                                        folder: "library-FLG26/components/",
                                    }
                                },
                                {
                                    module: "textGeneric",
                                    data: {
                                        styles: {
                                            top: 30,
                                            left: 186
                                        },
                                        text: "combos",
                                        classes: "headline",

                                    },
                                },
                                {
                                    module: "textGeneric",
                                    data: {
                                        styles: {
                                            top: 30,
                                            left: 788
                                        },
                                        text: "Incl. Reg. Chips and<br>Reg. Pepsi Max",
                                        classes: "subheadline",
                                        overrides: {
                                            "line-height": "130%"
                                        }

                                    },
                                },
                                // hero 
                                // 3 ww combo
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 166,
                                            left: 237,
                                            width: 450
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: 1,
                                                        left: -27,
                                                        scale: 1,
                                                        layer: 300,
                                                        position: "absolute"
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10 bg-green product-callout border-25",
                                                    styles: {
                                                        padding: [28, 42, 32, 50]
                                                    },
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "3 wicked<br>wings<span class='super'>&reg;</span><br>combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {

                                                                code: "11873",
                                                                classes: "price red lg"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {

                                                                code: "11873",
                                                                classes: "energy lg"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },
                                            {
                                                module: "textPriceFormat",
                                                data: {
                                                    code: "11873",
                                                    classes: "price callout white bg-red border-15",
                                                    styles: {
                                                        padding: [20],
                                                        left: 517,
                                                        top: 259,
                                                        layer: 110
                                                    },
                                                },
                                            },
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "cup-pepsi-combo",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -170,
                                                                    left: -370,
                                                                    scale: 0.74,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "can-pepsi-combo-v2",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -121,
                                                                    left: -186,
                                                                    scale: 0.8,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "3pcs-wicked-wings",
                                                    folder: "library-FLG26/chicken/",
                                                    classes: "",
                                                    styles: {
                                                        top: -36,
                                                        left: -227,
                                                        scale: 0.8,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                                // combos
                                // 3pc wicked boneless
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1414,
                                            left: 168,
                                            width: 400
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: 1,
                                                        left: -27,
                                                        scale: 1,
                                                        layer: 300
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "3 pcs. wicked<br>boneless<br>combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA134",
                                                                classes: "price lg red"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "NA134",
                                                                classes: "energy lg"
                                                            }
                                                        }
                                                    ]
                                                }
                                            },
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "cup-pepsi-combo",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -860,
                                                                    left: -660,
                                                                    scale: 0.42,
                                                                    layer: 101
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "can-pepsi-combo-v2",
                                                                folder: "library-FLG26/sides/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -782,
                                                                    left: -436,
                                                                    scale: 0.46,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            },

                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "3pcs-wicked-boneless",
                                                    folder: "library-FLG26/chicken/",
                                                    classes: "",
                                                    styles: {
                                                        top: -741,
                                                        left: -466,
                                                        scale: 0.44,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                                // 3pcs og tenders
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1414,
                                            left: 640,
                                            width: 330
                                        },
                                        modules: [
                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "bigger-lozenge",
                                                    folder: "library-FLG26/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: -44,
                                                        left: 25,
                                                        scale: 1.3,
                                                        layer: 300
                                                    },
                                                }
                                            },
                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-10",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "3 Tenders Dip'd combo",
                                                                classes: "name lg"
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA136",
                                                                classes: "price lg red"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "NA136",
                                                                classes: "energy lg"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },
                                            // old style
                                            // {
                                            //     module: "moduleIf",
                                            //     data: {
                                            //         type: "anyTag",
                                            //         condition: ["cups_condition"],
                                            //         ifTrue: [
                                            //             {
                                            //                 module: "moduleImage",
                                            //                 data: {
                                            //                     name: "3pcs-original-tenders-combo-cup",
                                            //                     folder: "library-FLG26/chicken/",
                                            //                     classes: "",
                                            //                     styles: {
                                            //                         top: -844,
                                            //                         left: -466,
                                            //                         scale: 0.44,
                                            //                         layer: 100
                                            //                     },
                                            //                 }
                                            //             }
                                            //         ],
                                            //         ifFalse: [
                                            //             {
                                            //                 module: "moduleImage",
                                            //                 data: {
                                            //                     name: "3pcs-original-tenders-combo",
                                            //                     folder: "library-FLG26/chicken/",
                                            //                     classes: "",
                                            //                     styles: {
                                            //                         top: -844,
                                            //                         left: -466,
                                            //                         scale: 0.44,
                                            //                         layer: 100
                                            //                     },
                                            //                 }
                                            //             }
                                            //         ]
                                            //     }
                                            // }
                                            //angled style
                                            {
                                                module: "moduleIf",
                                                data: {
                                                    type: "anyTag",
                                                    condition: ["cups_condition"],
                                                    ifTrue: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "3pcs-original-tenders-combo-cup-angled",
                                                                folder: "library-FLG26/chicken/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -834,
                                                                    left: -466,
                                                                    scale: 0.48,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ],
                                                    ifFalse: [
                                                        {
                                                            module: "moduleImage",
                                                            data: {
                                                                name: "3pcs-original-tenders-combo-angled",
                                                                folder: "library-FLG26/chicken/",
                                                                classes: "",
                                                                styles: {
                                                                    top: -834,
                                                                    left: -466,
                                                                    scale: 0.48,
                                                                    layer: 100
                                                                },
                                                            }
                                                        }
                                                    ]
                                                }
                                            }
                                        ]
                                    }
                                },
                                //ALC
                                // 3 wicked wings
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1766,
                                            left: 256,
                                            width: 200
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: -7,
                                                        left: -20,
                                                        scale: 0.6,
                                                        layer: 300
                                                    },
                                                }
                                            },

                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-5",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "3 wicked<br>wings<span class='super'>&reg;</span>",
                                                                classes: "name sm",
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "I-30017",
                                                                classes: "price sm red"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "I-30017",
                                                                classes: "energy sm"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },

                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "3pcs-wicked-wings",
                                                    folder: "library-FLG26/chicken/",
                                                    classes: "",
                                                    styles: {
                                                        top: -559,
                                                        left: -731,
                                                        scale: 0.19,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                },
                                // 3pcs. wicked boneless
                                {
                                    module: "groupContainer",
                                    data: {
                                        styles: {
                                            top: 1766,
                                            left: 560,
                                            width: 200
                                        },
                                        modules: [
                                            {
                                                module: "moduleVector",
                                                data: {
                                                    name: "flame-vector",
                                                    folder: "library-FLG25/components/",
                                                    classes: "",
                                                    styles: {
                                                        top: -7,
                                                        left: -20,
                                                        scale: 0.6,
                                                        layer: 300
                                                    },
                                                }
                                            },

                                            {
                                                module: "groupContainer",
                                                data: {
                                                    classes: "grid cols-1 gap-5",
                                                    modules: [
                                                        {
                                                            module: "textGeneric",
                                                            data: {
                                                                text: "3 pcs.<br>wicked<br>boneless",
                                                                classes: "name sm",
                                                            }
                                                        },
                                                        {
                                                            module: "textPriceFormat",
                                                            data: {
                                                                code: "NA131",
                                                                classes: "price sm red"
                                                            },
                                                        },
                                                        {
                                                            module: "textEnergyFormat",
                                                            data: {
                                                                code: "NA131",
                                                                classes: "energy sm"
                                                            }
                                                        },
                                                    ]
                                                }
                                            },

                                            {
                                                module: "moduleImage",
                                                data: {
                                                    name: "3pcs-wicked-boneless",
                                                    folder: "library-FLG26/chicken/",
                                                    classes: "",
                                                    styles: {
                                                        top: -557,
                                                        left: -703,
                                                        scale: 0.195,
                                                        layer: 300
                                                    },
                                                }
                                            }
                                        ]
                                    }
                                }
                            ]
                        },
                    },
                ],
            },
        }
    ]
}