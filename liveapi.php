<?php
header('Content-Type: application/json');
header('Cache-Control: no-cache, must-revalidate'); // Prevent caching
header('Expires: 0');

// Function to generate random live data
function getLiveScore() {
    $titles = ['Team A vs Team B', 'Team C vs Team D', 'Team E vs Team F'];
    $subtitles = ['Match 1', 'Match 2', 'Match 3'];

    // Pick a random title and subtitle
    $title = $titles[array_rand($titles)];
    $subtitle = $subtitles[array_rand($subtitles)];

    // Generate random scores
    $scoreA = rand(50, 150);
    $scoreB = rand(50, 150);

    // Build the response
    $response = [
        'title' => $title,
        'subtitle' => $subtitle,
        'score' => [
            'teamA' => $scoreA,
            'teamB' => $scoreB
        ]
    ];

    return json_encode($response);
}

// Output the live data
echo getLiveScore();
?>
