import fs from 'fs';

const response = await fetch("https://tamu.collegescheduler.com/api/terms/Fall%202025%20-%20College%20Station/subjects/CSCE/courses/120/regblocks", {
  "headers": {
    "accept": "*/*",
    "accept-language": "en-US,en;q=0.9,ru;q=0.8",
    "priority": "u=1, i",
    "sec-ch-ua": "\"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"",
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": "\"macOS\"",
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin",
    // "cookie": "__RequestVerificationToken=XWP-EiCo9qfZUB8-7Y2utb02X2wPpzUrHzO4ag2TcBAogSYm7i4oVrR1HR8N0j1vYYKXQsLx77vuHQL8d--GPb5MrVo1; _ga=GA1.1.958736496.1753707044; .AspNet.Cookies=3ylJYHJawE8Hq8bcgbPqrExY3o5GCg3MNGqbPCxwI7we5XYtJckFNWOidDr3UsEojKiQhs6go89VTrY5M3cniC8MSoL7qNt6iUT0JC0VkFzsWeq0hoxCRxZ6Uasyy-sbYURi0kqPCwFjkmujmHtjScQfZxuetGWBnq5D9yTBgR3X0K5xpwPx5ow7rXzdDZyv2LQ_xYwqONRIdMxlw4xPTmXUw3vYFvlwhaWQaW8d71VW9CgfvLjLBCTnbW-FEcUWD67V6Wj4lhst2llJpqfhLTmRTz6jr-II49WTTyUvgiHgc57fQVTDaVMKDiZ9O7LDuuI5p1gdzFopV_0_FFP4sI9o60eE6G4H4uEdDxL6NoxSS_tdQs08DMUQfntNhe5WM6rLQ1k07GKsvsQgwA7NwQF-KGUnq5BqWM1x3GqhCU4vv3a_l6b9mtXfUuCaRV6QiyJO7ZMV-EbJHMKOxkgxig9GyLrLZ2gRZ-CUrKYFsNC3Xx3FTgN1GGOFHPRCQEXRxBTElnKi6BDZxH4au8VEELubBykhlT6cchTlT01GuFgJApZQyEE6tImkw9iOOxnqdor5nrf3HDbZ0ghfXxxWnVGcIUGoEaDlx_27OhO7F1rpPoJsDYry1onYkaFnBfOKUTn7J5dJ432h_xVY2Yt5G_QYQo_wKYkPbFRxqAsz6W-CQOOTje6IExWJ4JSsUwG9kW7T9QVosA1Qq79kycKKoF3PaCO1w8mSzMej3WIuKKqQ5yu7UdTHxM1L5PZVDOaiJHgEQDAgqFomvtZ8L4y28RWTJrV0PHDcoqTB_ym3GiPGQ-mORSPcJYZqMvzlYWXLpqpLc079tj8s8jCumXGjw0AxY0uTa2scxTKsl0DSE2jt-4BUMnBlTnfylGMl35wFhtzd1NvG4IUInpSQFQhf1o7CU0F02QGY4rdCKEqo3sciESgusmZQuDr0RDbgTCMUzx8dvExqUmVvgJnvh5WBktWfjIPtwtTUEr9YTnrmmCwFzKVJQJYylGpkTRT3Vh4LVkHlJ5XjsODFIznogVxes9C6pEArYCs9UZiIxGPZ_zWNd4MubvnvaicVBeuQV-v-ngdfvdsB8NBmAOn7zo8DxjZfyVfEocbgZYl-4bX0Mfi_x4T4jRB2G3HjzU19ORSl63ZSuowPNkzYzj6d_c8Tz692ZypirjaH3oWrAqr89CvkpgcrLpYDAML26m6RjCXJa1nsJRpX9JiI80x5iTFRsAhUc_GDaLQ9KRLe__c_uGS0U6EAMEZA9f6fzI-YVEKfu6feF9XyhfzTQJOtH2SklsIXQ3Hxe97gRs89zxHFz9tqbxXRa2jv1Fvf1eHjfU4QP0oajvrcLrelIA-gimHgbG_ZqkBeCVPXZ_1XLeBV0t2nKgI27-NpKLWnQERgv16xG6RlhBM7yvVjwOqTf_euXzj3nHnTbyhmM1dx-BSx9PqArv4OI4OB6Gp76ibocK7BCqMsCFcjofiZAdrRfnb3eUU6WOIZOaIV7xrgSiAUD5PcMpUZRjMX8j-Gub4auUpji9NXVziwuPVPHog_VFSIRI0Wp9ckvzyYFdmoLhbwVuVnzT582kZRmNy0vKyQUoMoTAp5v7SUT6-RNGDkT1q0klGjpHmaHsNZXOHQjfSo7tJXoZpZDKJrM8Iraayt8C59NFdEj4ufbsOjXAmRn8O9i9L5tGE63xPvnSb1q6bZ4rRGszaz4T2qs5sH0sltmS8r7o-DokUpebwn_Z0GwaNV-ZO34uZyvc0XwwcLW7tdeCRbsN9MGzxjSXDgCeJ3sNf_-shL1qE2SnK8Cm8dlEfvEF5LkmPv4QZlqoeq0QQX1grM_n-qZiPebjd-9U0KBg91xK4lQRFHbxb52iNbsvAfTf_W1wsesWxRx8mC9nya2Ig2QH0EeJNxXNlSbHCDaRq_ZaNQeg; _ga_GZ44FZWHSB=GS2.1.s1756314617$o6$g1$t1756314664$j13$l0$h0",
    "Referer": "https://tamu.collegescheduler.com/terms/Fall%202025%20-%20College%20Station/courses/7585674"
  },
  "body": null,
  "method": "GET"
});
const data = await response.json();
fs.writeFileSync("output.json", JSON.stringify(data, null, 2));

//cookie could be messed up, referer has to be constantly changed