package com.lahiru.knowledgehub.dashboard;

public record DashboardResponse(
        long collectionsCount,
        long resourcesCount,
        long tagsCount
) {
}