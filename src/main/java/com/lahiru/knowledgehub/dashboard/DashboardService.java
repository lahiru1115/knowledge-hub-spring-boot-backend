package com.lahiru.knowledgehub.dashboard;

import com.lahiru.knowledgehub.collection.CollectionRepository;
import com.lahiru.knowledgehub.resource.ResourceRepository;
import com.lahiru.knowledgehub.tag.TagRepository;
import com.lahiru.knowledgehub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CollectionRepository collectionRepository;
    private final ResourceRepository resourceRepository;
    private final TagRepository tagRepository;

    public DashboardResponse getDashboard(User user) {
        return new DashboardResponse(
                collectionRepository.countByUserId(user.getId()),
                resourceRepository.countByCollectionUserId(user.getId()),
                tagRepository.count()
        );
    }
}