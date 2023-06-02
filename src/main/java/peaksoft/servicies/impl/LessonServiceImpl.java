package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.LessonRepository;
import peaksoft.servicies.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " is not found!"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
//        Lesson lesson = Lesson.builder()
//                .lessonName(lessonRequest.getLessonName()).build();
        lesson.setCourse(course);
        course.getLessons().add(lesson);
        lessonRepository.save(lesson);
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName()
        );
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public LessonResponse findLessonById(Long lessonId) {
        return lessonRepository.findLessonById(lessonId).orElseThrow(() -> new NoSuchElementException("ERROR"));
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id " + lessonId + " is not found."));
        lesson.setLessonName(lessonRequest.getLessonName());
        lessonRepository.save(lesson);
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName()
        );
    }

    @Override
    public SimpleResponse deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("lesson by id is %s is successfully deleted!!!",lessonId))
                .build();
    }
}
